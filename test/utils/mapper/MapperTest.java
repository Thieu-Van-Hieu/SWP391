/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package utils.mapper;

import myexception.MappingException;
import org.junit.Test;
import utils.Mapper;

/**
 *
 * @author quann
 */
public class MapperTest {
	
	@Test
    public void testSameType() {
        Source source = new Source(10, "Hello", 3.14);
        Target1 target = new Target1();

        Mapper.map(source, target);

        assertEquals(10, target.getX());
        assertEquals("Hello", target.getY());
    }

    @Test
    public void testIntToDouble() {
        Source source = new Source(10, "Hello", 3.14);
        Target2 target = new Target2();

        Mapper.map(source, target);

        assertEquals(10, target.getX(), 0.0);
        assertEquals("Hello", target.getY());
    }

    @Test
    public void testDoubleToString() {
        Source source = new Source(10, "Hello", 3.14);
        Target3 target = new Target3();

        assertThrows(MappingException.class, () -> {
            Mapper.map(source, target);
        });
    }
	
}
