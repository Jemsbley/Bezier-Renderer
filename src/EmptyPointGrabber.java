/**
 * Represents an empty/nonexistent point being grabbed. This allows the program to avoid
 * holding null values when the mouse does not click close enough to a point
 */
public class EmptyPointGrabber implements OptionalPointGrabber {

  /**
   * An empty point grabber is not a point grabber.
   * @return false
   */
  public boolean isPointGrabber() {
    return false;
  }

  /**
   * An empty point grabber cannot be unwrapped.
   * @return nothing
   * @throws IllegalStateException always, since this object cannot unwrap
   */
  public PointGrabber unwrap() {
    throw new IllegalStateException("Cannot unwrap empty point grabber");
  }
}
