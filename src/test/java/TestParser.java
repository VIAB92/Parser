import com.netcracker.training.app4.urlparser.ProductTypeParser;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Виктор on 12/28/13.
 */
public class TestParser {
    @Test
    public void testParserRelevance()
    {
        ProductTypeParser parser = new ProductTypeParser("http://rozetka.com.ua/headsets/c80032/");
        boolean isRelevant = parser.checkRelevance();
        Assert.assertTrue(isRelevant);
    }

    @Test
    public void testParserUnRelevance()
    {
        ProductTypeParser parser = new ProductTypeParser("http://google.ru");
        boolean isRelevant = parser.checkRelevance();
        Assert.assertFalse(isRelevant);
    }

    @Test
    public void testParseType()
    {
        ProductTypeParser parser = new ProductTypeParser("http://rozetka.com.ua/headsets/c80032/");
        parser.parseType("com.ua/");
        String type = parser.getProductType();
        Assert.assertEquals("Types are not equal", "headsets", type);
    }
}
