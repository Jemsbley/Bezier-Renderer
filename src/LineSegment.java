/**
 * Represents a line segment in 2D space represented by 2 points
 */
public class LineSegment {

  // The start and end points of the line segment
  public final Position start, end;

  /**
   * Initializes a line segment.
   * @param start the start point
   * @param end the end point
   */
  public LineSegment(Position start, Position end) {
    this.start = start;
    this.end = end;
  }

  /**
   * Gets the position on the line at the given x coordinate if the coordinate is on the line.
   * @param x the x coordinate on the line
   * @return the full position at that x coordinate on this line segment
   * @throws IllegalArgumentException if the x coordinate is not on the line
   */
  public Position getPositionAtX(double x) {
    // A point is not on the line segment if it is further than both endpoints in either direction
    // Since it is possible to construct a line with the end having a lesser x coordinate than the
    // start, we must conduct both checks here
    if ((this.end.getX() > x && this.start.getX() > x)
            || (this.end.getX() < x && this.start.getX() < x)) {
      throw new IllegalArgumentException("Invalid X Coordinate");
    }
    return new Position(x, this.slope() * (this.start.getX() - x) + this.start.getY());
  }

  /**
   * Calculates the slope of this line segment.
   * @return the slope of this line segment.
   */
  public double slope() {
    return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
  }
}
