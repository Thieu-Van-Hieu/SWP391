/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package utils.request_mapper;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Map;
import testkit.HttpServletRequestSimulator;
import utils.RequestMapper;

/**
 *
 * @author quann
 */
public class RequestMapperTest {

    @Test
    public void testMapToObject() {
		Map<String, String> params = Map.of(
		"string_field", "Mr.NoBody",
		"int_field", "20",
		"float_field", "3.14",
		"boolean_field", "true"
		);
        HttpServletRequestSimulator request = HttpServletRequestSimulator.builder().params(params).build();

        Target1 target = RequestMapper.mapToObject(request.getRequest(), Target1.class);

        assertEquals("Mr.NoBody", target.getString_field());
        assertEquals(20, target.getInt_field());
        assertEquals(3.14, target.getFloat_field(), 0.01);
        assertEquals(true, target.isBoolean_field());
    }

}
