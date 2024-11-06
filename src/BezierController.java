import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Controls the Bézier curve by reading mouse inputs.
 */
public class BezierController {

  // Stores a bézier to be controlled
  private final CubicBezier cb;

  // The view in which we will see any changes
  private final LinesView lView;

  // An Optional object that may hold one of the four points of the curve
  private OptionalPointGrabber grabber;

  /**
   * Constructs a new controller.
   * @param cb the bézier to be controlled
   * @param lView the view to show any changes
   */
  public BezierController(CubicBezier cb, LinesView lView) {
    this.cb = cb;
    this.grabber = new EmptyPointGrabber();
    this.lView = lView;

    // Create mouse listeners to actually implement controls
    this.lView.addMouseListener(this.getMouseListener());
    this.lView.addMouseMotionListener(this.getMouseListener());
  }

  /**
   * Creates an instance of the bézier mouse listener to be added to the view.
   * @return a new BézierMouseListener
   */
  public BezierMouseListener getMouseListener() {
    return new BezierMouseListener();
  }

  /**
   * Implementation of the MouseListener and MouseMotionListener so we can interpret mouse inputs
   */
  private class BezierMouseListener implements MouseListener, MouseMotionListener {

    // Note that most of the implementations below are empty as only holding and dragging
    // the mouse are relevant to this program

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    // When the mouse is pressed, we must check if the mouse is within reasonable distance
    // Of one of the bézier control points. If so, grab that one by putting it in the
    // PointGrabber of the controller
    @Override
    public void mousePressed(MouseEvent e) {
      for (Position p : cb.getPoints()) {
        if (p.isNear(new Position(e.getX(), LinesView.FRAME_SIZE - e.getY()), 20)) {
          grabber = new PointGrabber(p);
        }
      }
    }

    // When the mouse is released, make sure to remove the point that was grabbed
    // (if there is one) from the grabber so the mouse does not continue to move it. We also
    // must ensure that the point remains on the screen, as the user technically can drag their
    // mouse pointer off of the window while holding a point. To solve this issue we simply bound
    // off the coordinate of the grabbed point to just slightly smaller than the coordinates of
    // the screen
    @Override
    public void mouseReleased(MouseEvent e) {
      if (grabber.isPointGrabber()) {
        grabber.unwrap().getGrabbed()
                .boundOff(new Position(LinesView.FRAME_SIZE - 30, LinesView.FRAME_SIZE - 30));
      }
      lView.repaint();
      grabber = new EmptyPointGrabber();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // If a point is behind held while the mouse is being dragged, we move that grabbed point
    // to the coordinate of the mouse in logical space
    @Override
    public void mouseDragged(MouseEvent e) {
      if (grabber.isPointGrabber()) {
        Position toMove = grabber.unwrap().getGrabbed();
        toMove.moveTo(new Position(e.getX() - 5, 500 - e.getY() - 5));
        lView.repaint();
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
  }

}
