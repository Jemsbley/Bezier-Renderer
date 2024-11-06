import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

/**
 * Represents the visual panel that the bézier curve will be drawn on
 */
public class LinesPanel extends JPanel {

  // We must hold a bézier in order to draw it
  private CubicBezier cb;

  // A list of the control points of the bézier so they can be distinctly drawn
  private final List<Position> drawnPoints;

  /**
   * Constructs a new lines panel.
   * @param cb the cubic bézier to be read from
   */
  public LinesPanel(CubicBezier cb) {
    this.cb = cb;
    this.drawnPoints = new ArrayList<>();
    this.drawnPoints.addAll(Arrays.asList(cb.getPoints()));
  }

  /**
   * Draws the held bézier curve onto the panel
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Translate the drawing space of the graphics object to represent the first quadrant
    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(0, this.getHeight());
    g2d.scale(1, -1);
    g2d.setColor(Color.BLACK);

    // Initialize a list of lines to draw
    ArrayList<LineSegment> lines = new ArrayList<>();

    // Get the starting point of the curve
    Position prev = cb.getPointAt(0);

    // Here we will sample the curve 100 times. Note that these samples are not technically
    // equal length intervals, since the Bernstein equation is not linear.
    // We will draw 100 different line segments each at an additional 100th increment of t
    for (int i = 1; i <= 100; i += 1) {
      double t = (double) i / 100;
      lines.add(new LineSegment(prev, cb.getPointAt(t)));
      prev = cb.getPointAt(t);
    }

    // Then draw each of those lines
    for (LineSegment l : lines) {
      g2d.drawLine(
              (int) l.start.getX(), (int) l.start.getY(),
              (int) l.end.getX(), (int) l.end.getY());
    }

    // Below we simply draw lines connecting each pair of the control points and the control
    // points themselves
    g2d.setColor(new Color(0, 0, 1, (float) 0.6));

    Position[] points = cb.getPoints();

    g2d.drawLine(
            (int) points[0].getX(), (int) points[0].getY(),
            (int) points[1].getX(), (int) points[1].getY());

    g2d.drawLine(
            (int) points[2].getX(), (int) points[2].getY(),
            (int) points[3].getX(), (int) points[3].getY());


    for (Position p : this.drawnPoints) {
      g2d.setColor(Color.RED);
      g2d.fillOval(((int) p.getX()) - 5, ((int) p.getY()) - 5, 10, 10);
      g2d.setColor(new Color(100, 0, 0));
      g2d.drawOval(((int) p.getX()) - 5, ((int) p.getY()) - 5, 10, 10);
    }
  }




}
