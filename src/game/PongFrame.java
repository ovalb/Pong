package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PongFrame extends JFrame implements Runnable {
    private GamePanel canvas;
    private StatusPanel statusBar;
    private MenuPanel menuBar;

    private GameMenu menu;

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

        menu = new GameMenu();

        canvas = new GamePanel();
        statusBar = new StatusPanel();
        menuBar = new MenuPanel();

        statusBar.setPreferredSize(new Dimension(0, 50));
        menuBar.setPreferredSize(new Dimension(0, 30));

        add(menu, BorderLayout.CENTER);

//        add(menuBar, BorderLayout.NORTH);
//        add(canvas, BorderLayout.CENTER);
//        add(statusBar, BorderLayout.SOUTH);

        //Keybindings for First player
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UP");
        canvas.getActionMap().put("UP", new commandP1(Direction.UP));

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
        canvas.getActionMap().put("DOWN", new commandP1(Direction.DOWN));

        //Keybindings for Second player (optional)
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("COMMA"), "COMMA");
        canvas.getActionMap().put("COMMA", new commandP2(Direction.UP));

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("PERIOD"), "PERIOD");
        canvas.getActionMap().put("PERIOD", new commandP2(Direction.DOWN));

        setVisible(true);

        t = new Thread(this);
//        t.start();
    }

    @Override
    public void run() {
        scoreLeft = 0; scoreRight = 0;
        canvas.setDifficulty(Difficulty.HARD);

        multiplayer = JOptionPane.showConfirmDialog(this, "Want to play against a bot?",
                                                    "Multiplayer? ", JOptionPane.YES_NO_OPTION);
        if (multiplayer == 0) {
            canvas.setAuto(true);
            canvas.getActionMap().remove("COMMA");
            canvas.getActionMap().remove("PERIOD");
        }
        else canvas.setAuto(false);


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

    private class commandP1 extends AbstractAction {
        private Direction direction;

        commandP1(Direction direction) {
            this.direction = direction;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (direction == Direction.UP)
                canvas.movePaddle(canvas.getRightPaddle(), Direction.UP);
            else
                canvas.movePaddle(canvas.getRightPaddle(), Direction.DOWN);
        }
    }

    private class commandP2 extends AbstractAction {
        private Direction direction;

        commandP2(Direction direction) {
            this.direction = direction;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (direction == Direction.UP)
                canvas.movePaddle(canvas.getLeftPaddle(), Direction.UP);
            else
                canvas.movePaddle(canvas.getLeftPaddle(), Direction.DOWN);
        }
    }
}
