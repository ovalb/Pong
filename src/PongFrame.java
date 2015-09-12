import javax.swing.*;
import java.awt.*;

public class PongFrame extends JFrame {
    private GamePanel canvas;
    private StatusPanel statusBar;

    private final int HEIGHT = 600;
    private final int WIDTH = 600;

    PongFrame(String title) {
        super(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        canvas = new GamePanel();
        statusBar = new StatusPanel();

        canvas.setBackground(Color.BLACK);
        statusBar.setPreferredSize(new Dimension(0, 50));

        add(canvas, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        canvas.repaint();

        setVisible(true);
    }
}
