package pv260.unittesting;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.CatchException.verifyException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class CatchExceptionTest {

        private static final int INVALID_CTOR_ARG = -1;

    private static final int VALID_CTOR_ARG = 10;

    private static final String INVALID_METHOD_ARG = null;
    @Test
    public void testExceptionInConstructor() throws Exception {
        verifyException(() -> new MyObject(INVALID_CTOR_ARG));
        //the cast is required if we wish to use caughtException()
        //inside the matcher directly as Java cant infer the type correctly here
        assertThat((Exception) caughtException()).isInstanceOf(IllegalArgumentException.class)
                                                 .hasMessage("ctor");
    }

    @Test
    public void testExceptionInMethod() throws Exception{
        MyObject testedObject = new MyObject(VALID_CTOR_ARG);
        verifyException(() -> testedObject.myMethod(INVALID_METHOD_ARG));
        assertThat((Exception) caughtException()).isInstanceOf(IllegalArgumentException.class)
                                                 .hasMessage("method");
    }
    private static class MyObject {

        public MyObject(int val) {
            if (val < 0) {
                throw new IllegalArgumentException("ctor");
            }
        }

        public void myMethod(String data) {
            if (data == null) {
                throw new IllegalArgumentException("method");
            }
        }
    }

}
