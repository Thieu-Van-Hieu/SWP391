/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package utils.request_mapper;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import jakarta.servlet.http.HttpServletRequest;
import utils.RequestMapper;

/**
 *
 * @author quann
 */
public class RequestMapperTest {

    @Test
    public void testMapToObject() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getParameter("string_field")).thenReturn("Mr.NoBody");
        when(mockRequest.getParameter("int_field")).thenReturn("20");
        when(mockRequest.getParameter("float_field")).thenReturn("3.14");
        when(mockRequest.getParameter("boolean_field")).thenReturn("true");

        Target1 target = RequestMapper.mapToObject(mockRequest, Target1.class);

        assertEquals("Mr.NoBody", target.getString_field());
        assertEquals(20, target.getInt_field());
        assertEquals(3.14, target.getFloat_field(), 0.01);
        assertEquals(true, target.isBoolean_field());
    }

}
