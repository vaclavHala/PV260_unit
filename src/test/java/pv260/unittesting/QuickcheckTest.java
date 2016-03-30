package pv260.unittesting;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.java.lang.Encoded.InCharset;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class QuickcheckTest {

    @Property
    public void testSplit(@InCharset("ASCII") String phrase) {
        StringSplitter splitter = new BrokenSplitter();
        for (char symbol : phrase.toCharArray()) {
            List<String> split = splitter.split(symbol, phrase);
            assertEquals(phrase, unsplit(split, symbol));
        }
    }

    private String unsplit(List<String> parts, char symbol) {
        return parts.stream().collect(joining(String.valueOf(symbol)));
    }
}
