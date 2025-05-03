/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import exception.MappingException;
import exception.common.MissingDataException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TableMapper {

	public static <T> T mapToObject(String tableName, T source) throws MappingException, MissingDataException {
		Class<?> sourceClass = source.getClass();

		Field sourceFields[] = sourceClass.getDeclaredFields();
		ArrayList<Field> nullFields = new ArrayList<>();
		for (Field field : sourceFields) {
			try {
				if (field.get(source) == null) {
					nullFields.add(field);
				}
			} catch (Exception e) {
				throw new MappingException(String.format("Fail to get field % from object %s", field.getName(), sourceClass.getName()));
			}
		}
		
		if (sourceFields.length == nullFields.size())
		
		for (Field field: nullFields) {
			
		}
		
		return null;
	}
}
