package pv260.unittesting;

import static java.util.Arrays.asList;
import java.util.List;

public class BrokenSplitter implements StringSplitter {

    @Override
    public List<String> split(char symbol, String phrase) {
        return asList(phrase.split(String.valueOf(symbol)));
    }
}
