package pv260.unittesting;

import java.util.Objects;

public class Vector2D {

	public static final double EPSILON = 0.001;
	private static final double DEG_TO_RAD = 1.0 / 180.0 * Math.PI;
	private static final double RAD_TO_DEG = 1.0 / Math.PI * 180.0;

	private final double x;
	private final double y;

	private Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public static Vector2D fromCoordinates(double x, double y) {
		return new Vector2D(x, y);
	}

	public static Vector2D copyOf(Vector2D other) {
		return new Vector2D(other.x, other.y);
	}

	public static Vector2D betweenTwoPoints(double x1, double y1, double x2, double y2) {
		return new Vector2D(x2 - x1, y2 - y1);
	}

	public static Vector2D betweenTwoPoints(Vector2D from, Vector2D to) {
		return new Vector2D(to.x - from.x, to.y - from.y);
	}

	public static Vector2D fromAngleRadLength(double angleRad, double length) {
		double x = Math.cos(angleRad) * length;
		double y = Math.sin(angleRad) * length;
		return new Vector2D(x, y);
	}

	public static Vector2D fromAngleDegLength(double angleDeg, double length) {
		return fromAngleRadLength(angleDeg * DEG_TO_RAD, length);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getLength() {
		return Math.sqrt(x * x + y * y);
	}

	public double getAngleRad() {
		return Math.atan2(y, x);
	}

	public double getAngleDeg() {
		return getAngleRad() * RAD_TO_DEG;
	}

	public Vector2D normalized() {
		double lenght = getLength();
		return new Vector2D(x / lenght, y / lenght);
	}

	public Vector2D shiftedBy(double x, double y) {
		return new Vector2D(this.x + x, this.y + y);
	}

	public Vector2D shiftedBy(Vector2D other) {
		return shiftedBy(other.x, other.y);
	}

	public Vector2D scaledBy(double factor) {
		return new Vector2D(x * factor, y * factor);
	}

	public Vector2D rotatedByRad(double angleRad) {
		return Vector2D.fromAngleRadLength(
				getAngleRad() + angleRad, getLength());
	}

	public Vector2D rotatedByDeg(double angleDeg) {
		return rotatedByRad(angleDeg * DEG_TO_RAD);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vector2D)) {
			return false;
		}
		Vector2D other = (Vector2D) obj;
		return epsilonEquals(x, other.x) && epsilonEquals(y, other.y);
	}

	private boolean epsilonEquals(double a, double b) {
		return Math.abs(a - b) < EPSILON;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "<" + x + "," + y + ">";
	}

}
