package utils.mapper;

import exception.utils.MappingException;
import org.junit.Assert;
import org.junit.Test;
import utils.Mapper;

public class MapperTest {

    @Test
    public void testSameType() {
        Source source = new Source(10, "Hello", 3.14);

        Target1 target = Mapper.mapToObject(source, Target1.class);

        Assert.assertEquals(10, target.getX());
        Assert.assertEquals("Hello", target.getY());
    }

    @Test
    public void testIntToDouble() {
        Source source = new Source(10, "Hello", 3.14);

        Target2 target = Mapper.mapToObject(source, Target2.class);

        Assert.assertEquals(10, target.getX(), 0.0);
        Assert.assertEquals("Hello", target.getY());
    }

    @Test
    public void testDoubleToString() {
        Source source = new Source(10, "Hello", 3.14);

        Assert.assertThrows(MappingException.class, () -> {
            Target3 target = Mapper.mapToObject(source, Target3.class);
        });
    }

}
