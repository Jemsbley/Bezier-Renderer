/**
 * Represents a wrapper of a position in 2D space. This class is used to manipulate a control
 * point of the curve when it's clicked on
 */
public class PointGrabber implements OptionalPointGrabber{

  // The position that is wrapped
  private final Position grabbed;

  /**
   * Creates a Point Grabber with a given point. Note that the point is not copied so
   * mutation is mutual
   * @param point the point to be grabbed
   */
  public PointGrabber(Position point) {
    this.grabbed = point;
  }

  /**
   * Returns the stored point.
   * @return the stored point
   */
  public Position getGrabbed() {
    return grabbed;
  }

  /**
   * A PointGrabber is a PointGrabber.
   * @return true
   */
  public boolean isPointGrabber() {
    return true;
  }

  /**
   * A PointGrabber unwraps to itself.
   * @return this
   */
  public PointGrabber unwrap() {
    return this;
  }
}
