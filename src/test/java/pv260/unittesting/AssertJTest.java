package pv260.unittesting;

import static java.util.Arrays.asList;
import org.assertj.core.api.SoftAssertions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * These tests are meant to all fail as they demonstrate pretty error messages
 */
public class AssertJTest {

    @Test
    public void testStringMatch() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat("abc").startsWith("ab")
              .contains("e")
              .isEqualTo("abcd")
              .hasSize(4);
        softly.assertAll();
    }

    @Test
    public void testStringMatchJUnit() {
        String actual = "abc";
        assertTrue(actual.startsWith("ab"));
        assertTrue(actual.contains("e"));
        assertEquals("abcd", actual);
        assertTrue(actual.length() == 4);
    }

    @Test
    public void testCollectionContains() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(asList(1, 2, 3))
              .containsExactly(3, 2, 1)
              .contains(3, 1, 2)
              .containsOnly(1, 2)
              .hasSize(4);
        softly.assertAll();
    }
}
