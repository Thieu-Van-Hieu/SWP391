package utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import exception.MappingException;

public class Mapper {

    public static <T> T mapToObject(Object source, Class<T> clazz) throws MappingException {
        Class<?> sourceClass = source.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();
        Map<String, Object> sourceFieldsMap = new HashMap<>();
        for (Field field : sourceFields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                Object fieldData = field.get(source);

                sourceFieldsMap.put(fieldName, fieldData);
            } catch (Exception e) {
                throw new MappingException(String.format("Cannot get data of field %s from object %s", fieldName, sourceClass.getName()));
            }
        }
        try {
            T instance = clazz.getConstructor().newInstance();
            Field[] targetFields = clazz.getDeclaredFields();
            for (Field field : targetFields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldData = sourceFieldsMap.get(fieldName);
                if (fieldData == null) {
                    continue;
                }
                try {
                    field.set(instance, fieldData);
                } catch (Exception e) {
                    throw new MappingException(String.format("Cannot parse data of field %s (%s to %s) from object (%s to %s)", fieldName, fieldData.getClass().getName(), field.getType().getName(), sourceClass.getName(), clazz.getName()));
                }
            }

            return instance;
        } catch (Exception e) {
            throw new MappingException(String.format("Cannot create instance for class %s", clazz.getName()));
        }
    }

    public static <T> T mapObjectIgnoreNull(Object target, Object sourceData) throws MappingException {
        Field[] targetFields = target.getClass().getDeclaredFields();
        Field[] sourceDataFields = sourceData.getClass().getDeclaredFields();

        for (Field field : sourceDataFields) {
            field.setAccessible(true);
            try {
                Object value = field.get(sourceData);
                if (value != null) {
                    try {
                        Field targetField = target.getClass().getDeclaredField(field.getName());
                        targetField.setAccessible(true);
                        targetField.set(target, value);
                    } catch (NoSuchFieldException e) {
                    }

                }
            } catch (Exception e) {
                throw new MappingException("Failed ti access field: " + field.getName(), e);
            }

        }
        return (T) target;
    }
}
