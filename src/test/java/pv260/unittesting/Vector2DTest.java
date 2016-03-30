package pv260.unittesting;

import pv260.unittesting.Vector2D;
import com.googlecode.zohhak.api.Coercion;
import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import org.junit.runner.RunWith;
import static pv260.unittesting.Vector2D.EPSILON;

@RunWith(ZohhakRunner.class)
public class Vector2DTest {

    private static final double SQRT2 = 1.41421356;

    @TestWith({"45, 5.65685, 4;4",
               "171.8699, 7.07107, -7;1",
               "225, 1.41421, -1;-1",
               "296.56505, 4.47214, 2;-4"})
    public void testFromAngle(double angleDeg, double length, Vector2D expectedResult) {
        assertThat(Vector2D.fromAngleDegLength(angleDeg, length)).describedAs("Vector from angle <%s> with length <%s>",
                                                                              angleDeg, length)
                                                                 .isEqualTo(expectedResult);
    }

    @TestWith({"1;2, 3;4, 2;2",
               "-7;4, -4;-3, 3;-7"})
    public void testBetweenTwoPoints(Vector2D from, Vector2D to, Vector2D expectedResult) {
        assertThat(Vector2D.betweenTwoPoints(from, to)).isEqualTo(expectedResult);
    }

    @TestWith({"1;1, " + 45.0,
               "0;1, " + 90.0,
               "-1;-1, " + (-135.0)})
    public void testAngleDeg(Vector2D vector, double expectedAngleDeg) {
        assertThat(vector.getAngleDeg()).isCloseTo(expectedAngleDeg, offset(EPSILON));
    }

    @TestWith({"0;1, 1",
               "1;1, " + SQRT2,
               "-1;-1, " + SQRT2,
               "2;-3, 3.60555"})
    public void testLength(Vector2D vector, double expectedLength) {
        assertThat(vector.getLength()).isCloseTo(expectedLength, offset(EPSILON));
    }

    @TestWith({"0;10, 0;1",
               "-1;1, " + -(1 / SQRT2) + ";" + (1 / SQRT2)})
    public void testNormalized(Vector2D vector, Vector2D expectedResult) {
        assertThat(vector.normalized()).isEqualTo(expectedResult);
    }

    @TestWith({"0;0, 1;1, 1;1",
               "-1;3, 1;-3, 0;0"})
    public void testShiftedBy(Vector2D vector, Vector2D other, Vector2D expectedResult) {
        assertThat(vector.shiftedBy(other)).isEqualTo(expectedResult);
    }

    @TestWith({"1;0, 90, 0;1",
               "1;0, -90, 0;-1",
               "-1;-1, 45, 0;-1.41421"})
    public void testRotatedBy(Vector2D vector, double angleDeg, Vector2D expectedResult) {
        assertThat(vector.rotatedByDeg(angleDeg)).isEqualTo(expectedResult);
    }

    @Coercion
    public Vector2D coerceVector(String input) {
        String[] split = input.split(";");
        return Vector2D.fromCoordinates(Double.valueOf(split[0]),
                                        Double.valueOf(split[1]));
    }
}
