import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        addKeyListener(new KeyboardListener());

        add(canvas, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }
    // inner listener class
    private class KeyboardListener extends KeyAdapter {
        @Override public void keyPressed(KeyEvent e) {
            statusBar.score2.setText("U typed " + e.getKeyChar());
        }
    }
}
