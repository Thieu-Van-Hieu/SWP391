/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import myexception.MappingException;

public class Mapper {

	public static void map(Object source, Object target) throws MappingException {
		Class<?> sourceClass = source.getClass();
		Class<?> targetClass = target.getClass();

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

		Field[] targetFields = targetClass.getDeclaredFields();
		for (Field field : targetFields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object fieldData = sourceFieldsMap.get(fieldName);
			try {
				field.set(target, fieldData);
			} catch (Exception e) {
				throw new MappingException(String.format("Cannot parse data of field %s (%s to %s) from object (%s to %s)", fieldName, fieldData.getClass().getName(), field.getType().getName(), sourceClass.getName(), targetClass.getName()));
			}
		}
	}
}
