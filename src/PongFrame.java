//package Pong.src;

import javax.swing.*;
import java.awt.*;

public class PongFrame extends JFrame {
    private JPanel canvas;
    private JPanel statusBar;

    PongFrame(String title) {
        super(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);

        canvas = new JPanel();
        statusBar = new JPanel();

        canvas.setBackground(Color.BLACK);
        statusBar.setBackground(Color.CYAN);
        statusBar.setPreferredSize(new Dimension(0, 50));

        add(canvas, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }
}
