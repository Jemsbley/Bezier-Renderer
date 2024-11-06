import javax.swing.JFrame;

/**
 * Represents the actual window that the program will run on.
 */
public class LinesView extends JFrame {

  // Final constant for the frame size
  public static final int FRAME_SIZE = 500;

  // LinesPanel to be held
  private LinesPanel lPanel;

  /**
   * Creates a new JFrame LinesView.
   * @param lPanel the panel to be viewed
   */
  public LinesView(LinesPanel lPanel) {
    super();
    this.lPanel = lPanel;
    this.add(this.lPanel);
    this.setTitle("Lines!");
    this.setSize(FRAME_SIZE, FRAME_SIZE);

    // Allows the program to shut down when the window is closed
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Prevents resizing of the window
    this.setResizable(false);
    this.setVisible(true);
  }

}
