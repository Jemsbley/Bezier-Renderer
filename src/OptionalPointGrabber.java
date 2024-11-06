/**
 * Represents a possible point being grabbed by a controller.
 */
public interface OptionalPointGrabber {

  /**
   * Determines if this OptionalPointGrabber contains a point.
   * @return true if this is an instance of PointGrabber, false otherwise
   */
  boolean isPointGrabber();

  /**
   * Unwraps this PointGrabber (assuming this object is an instance of PointGrabber).
   * @return this.
   * @throws IllegalStateException if this object is not a PointGrabber
   */
  PointGrabber unwrap();
}