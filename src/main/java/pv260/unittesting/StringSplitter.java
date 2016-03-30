
package pv260.unittesting;

import java.util.List;

public interface StringSplitter {

    /**
     * Split the phrase by symbol so that when the returned parts
     * are joined using the same symbol, the original phrase is obtained.
     * The returned parts are guaranteed to not contain the symbol.
     * Example:
     * <pre>split('x',"abcxdxe") = ["abc","d","e"]</pre>
     */
    List<String> split(char symbol, String phrase);
}
