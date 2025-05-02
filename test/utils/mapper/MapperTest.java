/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package utils.mapper;

import exception.MappingException;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Mapper;

/**
 *
 * @author quann
 */
public class MapperTest {

    @Test
    public void testSameType() {
        Source source = new Source(10, "Hello", 3.14);

        Target1 target = Mapper.mapToObject(source, Target1.class);

        assertEquals(10, target.getX());
        assertEquals("Hello", target.getY());
    }

    @Test
    public void testIntToDouble() {
        Source source = new Source(10, "Hello", 3.14);

        Target2 target = Mapper.mapToObject(source, Target2.class);

        assertEquals(10, target.getX(), 0.0);
        assertEquals("Hello", target.getY());
    }

    @Test
    public void testDoubleToString() {
        Source source = new Source(10, "Hello", 3.14);

        assertThrows(MappingException.class, () -> {
            Target3 target = Mapper.mapToObject(source, Target3.class);
        });
    }

}
