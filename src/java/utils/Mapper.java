/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import exceptions.MappingException;

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
}
