/**
 * Represents a cubic bézier. This model will hold four points representing the control
 * points of the bézier. A bézier curve is a curve along a domain of t over [0,1].
 * The curve follows the standard Bernstein formula for a bézier curve.
 */
public class CubicBezier {

  // The array of four points (in a specific order) that represent our control points
  private final Position[] points = new Position[4];

  /**
   * Creates a cubic bézier with the given set of points if there are exactly four points
   * provided.
   * @param points the array of control points to be used
   * @throws IllegalArgumentException if the array of points is not exactly 4 points long
   */
  public CubicBezier(Position[] points) {
    if (!(points.length == 4)) {
      throw new IllegalArgumentException("Need exactly four points");
    }

    // Here we make copies of each position from the given array so the bézier cannot be
    // affected by changes outside its controller
    for (int index = 0; index < 4; index += 1) {
      this.points[index] = new Position(points[index].getX(), points[index].getY());
    }
  }

  /**
   * Returns the array of control points. Changing this array will affect the bézier.
   * This is how the controller manipulates the points
   * @return the array of control points
   */
  public Position[] getPoints() {
    return this.points;
  }

  /**
   * Gets the point at a certain t value in the bézier.
   * @param t a double value on [0, 1] representing the input t to the Bernstein equation
   * @return the exact position along the curve outputted by the Bernstein equation for the given t
   * @throws IllegalArgumentException if t is below 0 or greater than 1
   */
  public Position getPointAt(double t) {
    if (t > 1 || t < 0) {
      throw new IllegalArgumentException("Invalid t");
    }

    /*
    Below is implementation of the Bernstein Equation
    (-1t^3 + 3t^2 - 3t + 1)P_0 +
    (3t^3 - 6t^2 + 3t)P_1 +
    (-3t^3 + 3t^2)P_2 +
    (t^3)P_3
     */

    Position p0Influence = points[0].multiply(-1 * t * t * t).addPosition(
            points[0].multiply(3 * t * t)).addPosition(
                    points[0].multiply(-3 * t)).addPosition(points[0]);

    Position p1Influence = points[1].multiply(3 * t * t * t).addPosition(
            points[1].multiply(-6 * t * t)).addPosition(
            points[1].multiply(3 * t));

    Position p2Influence = points[2].multiply(-3 * t * t * t).addPosition(
            points[2].multiply(3 * t * t));

    Position p3Influence = points[3].multiply(t * t * t);

    return p0Influence.addPosition(p1Influence.addPosition(p2Influence.addPosition(p3Influence)));
  }

}
