
package pv260.unittesting;

import static java.lang.String.format;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CSVFileInputTest {

    @Test
    @FileParameters("src/test/resources/test.csv")
    public void loadParamsFromCsv(double price, int credit, double discount) {
        System.out.println(format("price: %f, credit: %d, discount: %f", price, credit, discount));
        //assert that purchase of given price
        //from customer with given credit
        //receives some discount
        //...
    }

}
