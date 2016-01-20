package game;
import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PongFrame extends JFrame implements Runnable {
    private GamePanel canvas;
    private StatusPanel statusBar;
    private MenuPanel menuBar;

    private final int HEIGHT = 600;
    private final int WIDTH = 600;
    private final int MAX_SCORE = 3;

    private int scoreLeft;
    private int scoreRight;

    private int multiplayer;

    Thread t;

    PongFrame(String title) {
        super(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        canvas = new GamePanel();
        statusBar = new StatusPanel();
        menuBar = new MenuPanel();

        statusBar.setPreferredSize(new Dimension(0, 50));
        menuBar.setPreferredSize(new Dimension(0, 50));

        add(menuBar, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        addKeyListener(new KeyboardListener());

        setVisible(true);

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        scoreLeft = 0; scoreRight = 0;
        canvas.setDifficulty(Difficulty.HARD);

        multiplayer = JOptionPane.showConfirmDialog(this, "Want to play against a bot?",
                                                    "Multiplayer? ", JOptionPane.YES_NO_OPTION);
        if (multiplayer == 0) {//if YES
            canvas.setAuto(true);
//
//            JList list = new JList(new String[] {"foo", "bar", "gah"});
//            JOptionPane.showMessageDialog(
//                    null, list, "Multi-Select Example", JOptionPane.PLAIN_MESSAGE);

        }
        else
            canvas.setAuto(false);

        do {
            System.out.print("\nGame starting in: ");
            canvas.countDown(3);
            canvas.play();
            if (canvas.getWhoScored() == Player.LEFT)
                statusBar.scoreLeft.setText(String.valueOf(++scoreLeft));
            else
                statusBar.scoreRight.setText(String.valueOf(++scoreRight));

            canvas.reset();
            repaint();

        } while (scoreLeft != MAX_SCORE && scoreRight != MAX_SCORE);

        if (scoreLeft == MAX_SCORE)
            JOptionPane.showMessageDialog(this, "Player LEFT won!");

        else
            JOptionPane.showMessageDialog(this, "Player RIGHT won!");

        this.setVisible(false);
        this.dispose();
    }

    // inner listener class
    private class KeyboardListener extends KeyAdapter {
        @Override public void keyPressed(KeyEvent e) {
            if (!canvas.isAuto()) {
                if (e.getKeyCode() == KeyEvent.VK_COMMA)
                    canvas.movePaddle(canvas.getLeftPaddle(), Direction.UP);
                else if (e.getKeyCode() == KeyEvent.VK_PERIOD)
                    canvas.movePaddle(canvas.getLeftPaddle(), Direction.DOWN);
            }

            if (e.getKeyCode() == KeyEvent.VK_UP)
                canvas.movePaddle(canvas.getRightPaddle(), Direction.UP);
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                canvas.movePaddle(canvas.getRightPaddle(), Direction.DOWN);

            canvas.repaint();
        }
    }
}
