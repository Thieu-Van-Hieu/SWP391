package utils.requestmapper;

import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import testkit.HttpServletRequestSimulator;
import utils.RequestMapper;

public class RequestMapperTest {

    @Test
    public void testMapToObject() {
		Map<String, String> params = Map.of(
		"stringField", "Mr.NoBody",
		"intField", "20",
		"floatField", "3.14",
		"booleanField", "true"
		);
        HttpServletRequestSimulator request = HttpServletRequestSimulator.builder().params(params).build();

        Target1 target = RequestMapper.mapToObject(request.getRequest(), Target1.class);

        Assert.assertEquals("Mr.NoBody", target.getStringField());
        Assert.assertEquals(20, target.getIntField());
        Assert.assertEquals(3.14, target.getFloatField(), 0.01);
        Assert.assertEquals(true, target.isBooleanField());
    }

}
