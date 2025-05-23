package utils.tablemapper;

import config.DBContext;
import exception.utils.tablemapper.FailToCreateInstanceException;
import exception.utils.tablemapper.FailToInsert;
import exception.utils.tablemapper.IdColumnNotFoundException;
import exception.utils.tablemapper.ObjectNotFoundException;
import exception.utils.tablemapper.UnsupportedTypeException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.tablemapper.annotation.Column;
import utils.tablemapper.annotation.Table;

public class TableMapper {

    private static Connection connection = DBContext.getInstance().getConnection();

    private static String getNameOfTable(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Table.class) && !clazz.getAnnotation(Table.class).name().isBlank()) {
            return clazz.getAnnotation(Table.class).name();
        }

        return clazz.getSimpleName();
    }

    private static String getNameOfField(Field field) {
        if (field.isAnnotationPresent(Column.class) && !field.getAnnotation(Column.class).name().isBlank()) {
            return field.getAnnotation(Column.class).name();
        }

        return field.getName();
    }

    private static void setDataSql(int index, Object value, PreparedStatement statement) throws SQLException {
        String typeName = value.getClass().getName();

        switch (typeName) {
            case "boolean", "java.lang.Boolean" ->
                statement.setBoolean(index, (boolean) value);
            case "byte", "java.lang.Byte" ->
                statement.setByte(index, (byte) value);
            case "char", "java.lang.Character" ->
                statement.setString(index, String.valueOf(value));
            case "short", "java.lang.Short" ->
                statement.setShort(index, (short) value);
            case "int", "java.lang.Integer" ->
                statement.setInt(index, (int) value);
            case "long", "java.lang.Long" ->
                statement.setLong(index, (long) value);
            case "float", "java.lang.Float" ->
                statement.setFloat(index, (float) value);
            case "double", "java.lang.Double" ->
                statement.setDouble(index, (double) value);
            case "java.lang.String" ->
                statement.setString(index, (String) value);
            case "java.util.Date" ->
                statement.setDate(index, new java.sql.Date(((java.util.Date) value).getTime()));
            case "java.sql.Timestamp" ->
                statement.setTimestamp(index, (java.sql.Timestamp) value);
            default ->
                throw new UnsupportedTypeException(typeName);
        }
    }

    private static Object getDataFromColumn(int index, String typeName, ResultSet rs) throws SQLException {
        return switch (typeName) {
            case "boolean", "java.lang.Boolean" ->
                rs.getBoolean(index);
            case "byte", "java.lang.Byte" ->
                rs.getByte(index);
            case "char", "java.lang.Character" -> {
                String str = rs.getString(index);
                yield (str != null && !str.isEmpty()) ? str.charAt(0) : null;
            }
            case "short", "java.lang.Short" ->
                rs.getShort(index);
            case "int", "java.lang.Integer" ->
                rs.getInt(index);
            case "long", "java.lang.Long" ->
                rs.getLong(index);
            case "float", "java.lang.Float" ->
                rs.getFloat(index);
            case "double", "java.lang.Double" ->
                rs.getDouble(index);
            case "java.lang.String" ->
                rs.getString(index);
            case "java.util.Date" -> {
                java.sql.Date date = rs.getDate(index);
                yield (date != null) ? new java.util.Date(date.getTime()) : null;
            }
            case "java.sql.Timestamp" ->
                rs.getTimestamp(index);
            default ->
                throw new UnsupportedTypeException(typeName);
        };
    }

    private static void setDataSql(Field fields[], Object source, PreparedStatement statement) throws Exception {
        for (int i = 1; i <= fields.length; i++) {
            Field field = fields[i - 1];
            field.setAccessible(true);
            TableMapper.setDataSql(i, field.get(source), statement);
        }
    }

    private static <T> T getDataFromColumns(Class<T> clazz, ResultSet rs) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        T instance;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new FailToCreateInstanceException(clazz);
        }

        for (int i = 1; i <= fields.length; i++) {
            Field field = fields[i - 1];
            field.setAccessible(true);
            field.set(instance, getDataFromColumn(i, field.getType().getName(), rs));
        }

        return instance;
    }

    private static Field getIdField(Field fields[]) throws IdColumnNotFoundException {
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            String columnName = field.getAnnotation(Column.class).name();
            if (columnName.isBlank()) {
                continue;
            }

            if (columnName.equalsIgnoreCase("id")) {
                return field;
            }
        }

        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("id")) {
                return field;
            }
        }

        throw new IdColumnNotFoundException();
    }

    private static Field[] getNonIdField(Field fields[]) {
        Field idField = getIdField(fields);

        Field nonIdFields[] = new Field[fields.length - 1];
        int index = 0;
        for (Field field : fields) {
            if ("id".equalsIgnoreCase(getNameOfField(field))) {
                continue;
            }

            nonIdFields[index++] = field;
        }

        return nonIdFields;
    }

    public static <T> T get(Class<T> clazz, Object id) throws Exception {
        String tableName = getNameOfTable(clazz);

        Field[] fields = clazz.getDeclaredFields();

        Field idField = getIdField(fields);
        String idFieldName = getNameOfField(idField);

        String select = "";
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = getNameOfField(field);

            select += fieldName + (i < fields.length - 1 ? ", " : "");
        }

        String where = idFieldName + " = ?";

        String sql = String.format("select %s from %s where %s", select, tableName, where);
        PreparedStatement statement = connection.prepareStatement(sql);
        TableMapper.setDataSql(1, id, statement);

        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return getDataFromColumns(clazz, rs);
        }

        throw new ObjectNotFoundException(clazz);
    }

    public static <T> ArrayList<T> getAll(Class<T> clazz, Field[] filterFields, Object source) throws Exception {
        String tableName = getNameOfTable(clazz);

        Field[] fields = clazz.getDeclaredFields();

        String select = "";
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = getNameOfField(field);

            select += fieldName + (i < fields.length - 1 ? ", " : "");
        }

        String where = "";
        for (int i = 0; i < filterFields.length; i++) {
            Field field = filterFields[i];
            String fieldName = getNameOfField(field);

            where += fieldName + " = ?" + (i < filterFields.length - 1 ? " and " : "");
        }

        String sql = String.format("select %s from %s where %s", select, tableName, where);
        PreparedStatement statement = connection.prepareStatement(sql);
        setDataSql(filterFields, source, statement);

        ResultSet rs = statement.executeQuery();

        ArrayList<T> objects = new ArrayList<>();
        while (rs.next()) {
            objects.add(getDataFromColumns(clazz, rs));
        }

        if (objects.isEmpty()) {
            throw new ObjectNotFoundException(clazz);
        }

        return objects;
    }

    public static int create(Object object) throws Exception {
        Class<?> objectClass = object.getClass();

        String tableName = getNameOfTable(objectClass);

        Field fields[] = getNonIdField(objectClass.getDeclaredFields());

        String insertColumn = "";
        String value = "";
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            insertColumn += getNameOfField(field) + (i < fields.length - 1 ? ", " : "");
            value += "?" + (i < fields.length - 1 ? ", " : "");
        }

        String sql = String.format("insert into %s (%s) values (%s)", tableName, insertColumn, value);
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        setDataSql(fields, object, statement);
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

        throw new FailToInsert();
    }
    
    public static boolean update(Object object) throws Exception {
        Class<?> objectClass = object.getClass();
        
        String tableName = getNameOfTable(objectClass);
        Field fields[] = objectClass.getDeclaredFields();
        Field nonIdfields[] = getNonIdField(fields);
        
        String updateColumn = "";
        for (int i = 0; i < nonIdfields.length; i++) {
            Field field = nonIdfields[i];
            String fieldName = getNameOfField(field);
            
            updateColumn += fieldName + " = ?" + (i <nonIdfields.length - 1 ? ", " : "");
        }
        
        Field idField = getIdField(fields);
        idField.setAccessible(true);
        String idFieldName = getNameOfField(idField);
        String where = idFieldName + " = ?";
        
        String sql = String.format("update %s set %s where %s", tableName, updateColumn, where);
        PreparedStatement statement = connection.prepareStatement(sql);
        setDataSql(nonIdfields, object, statement);
        setDataSql(fields.length, idField.get(object), statement);
        
        return statement.executeUpdate() > 0;
    }
    
    public static boolean delete(Object object) throws Exception {
        Class<?> objectClass = object.getClass();
        
        String tableName = getNameOfTable(objectClass);
        Field fields[] = objectClass.getDeclaredFields();
        
        Field idField = getIdField(fields);
        idField.setAccessible(true);
        String idFieldName = getNameOfField(idField);
        String where = idFieldName + " = ?";
        
        String sql = String.format("delete from %s where %s", tableName, where);
        PreparedStatement statement = connection.prepareStatement(sql);
        setDataSql(1, idField.get(object), statement);
        
        return statement.executeUpdate() > 0;
    }

    public static void beginTransaction() throws Exception {
        connection.setAutoCommit(false);
    }

    public static void commitTransaction() throws Exception {
        connection.commit();
    }

    public static void rollbackTransaction() throws Exception {
        connection.rollback();
    }

    public static void close() throws Exception {
        connection.close();
    }
}
