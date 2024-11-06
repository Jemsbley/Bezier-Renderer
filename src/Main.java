/**
 * Main class to run the program.
 */
public class Main {
  public static void main(String[] args) {
    // An arbitrary set of control points
    Position[] points1 = new Position[4];
    points1[0] = new Position(100, 100);
    points1[2] = new Position(400, 75);
    points1[1] = new Position(115, 300);
    points1[3] = new Position(410, 410);

    // Create the model, view, and controller
    CubicBezier cb1 = new CubicBezier(points1);
    LinesPanel panel = new LinesPanel(cb1);
    LinesView view = new LinesView(panel);
    BezierController bc = new BezierController(cb1, view);
  }
}