package utils.requestmapper;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Map;
import testkit.HttpServletRequestSimulator;
import utils.RequestMapper;

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

        assertEquals("Mr.NoBody", target.getStringField());
        assertEquals(20, target.getIntField());
        assertEquals(3.14, target.getFloatField(), 0.01);
        assertEquals(true, target.isBooleanField());
    }

}
