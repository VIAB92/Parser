import com.netcracker.training.app4.reader.TextFileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Виктор on 12/28/13.
 */
public class TestReader {
    TextFileReader textFileReader;

    @Before
    public void makeItBefore()
    {
        textFileReader = new TextFileReader();
    }

    @Test
    public void testReaderWithCorrectPath() throws FileNotFoundException {
        List<String> urls = textFileReader.readFromPath("url.txt");
        Assert.assertEquals("Error - urls list is not valid", 3, urls.size());
    }

    @Test(expected = FileNotFoundException.class)
    public void testReaderWithIncorrectPath() throws FileNotFoundException {
        List<String> urls = textFileReader.readFromPath("trololo");

    }
}
