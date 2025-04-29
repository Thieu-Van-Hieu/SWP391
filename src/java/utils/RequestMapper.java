/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import myexception.RequestMappingException;

public class RequestMapper {

    public static <T> T mapToObject(HttpServletRequest request, Class<T> clazz) {
         try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String value = request.getParameter(field.getName());
                if (value != null) {
                    Class<?> fieldType = field.getType();
                    if (fieldType == String.class) {
                        field.set(instance, value);
                    } else if (fieldType == int.class || fieldType == Integer.class) {
                        field.set(instance, Integer.parseInt(value));
                    } else if (fieldType == long.class || fieldType == Long.class) {
                        field.set(instance, Long.parseLong(value));
                    } else if (fieldType == float.class || fieldType == Float.class) {
                        field.set(instance, Float.parseFloat(value));
                    } else if (fieldType == double.class || fieldType == Double.class) {
                        field.set(instance, Double.parseDouble(value));
                    } else if (fieldType == byte.class || fieldType == Byte.class) {
                        field.set(instance, Byte.parseByte(value));
                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                        field.set(instance, Boolean.parseBoolean(value));
                    } else if (fieldType == Date.class) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        field.set(instance, sdf.parse(value));
                    }
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RequestMappingException("Error mapping request parameters to object", e);
        }
    }
}
