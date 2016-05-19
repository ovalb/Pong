package game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class PongFrame extends JFrame implements Runnable {
    private GamePanel canvas;
    private StatusPanel statusBar;
    private MenuPanel menuBar;

    private StartMenu menu;
    private ResumeMenu resumeMenu;
    private SettingsMenu settings;

    private final int HEIGHT = 600;
    private final int WIDTH = 600;
    private final int MAX_SCORE = 3;

    private int scoreLeft;
    private int scoreRight;

    private boolean multiplayer = false;

    Thread t;

    PongFrame(String title) {
        super(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        menu = new StartMenu();
        resumeMenu = new ResumeMenu();
        settings = new SettingsMenu();

        canvas = new GamePanel();
        statusBar = new StatusPanel();
        menuBar = new MenuPanel();

        statusBar.setPreferredSize(new Dimension(0, 50));
        menuBar.setPreferredSize(new Dimension(0, 30));

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

        add(menu, BorderLayout.CENTER);
        t = new Thread(this);

        menu.getSingleplay().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplayer = false;
                remove(menu);
                add(menuBar, BorderLayout.NORTH);
                add(canvas, BorderLayout.CENTER);
                add(statusBar, BorderLayout.SOUTH);
                revalidate();
                repaint();
                t.start();
            }
        });

        menu.getMultiplay().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplayer = true;
                remove(menu);
                add(menuBar, BorderLayout.NORTH);
                add(canvas, BorderLayout.CENTER);
                add(statusBar, BorderLayout.SOUTH);
                revalidate();
                repaint();
                t.start();
            }
        });

        menu.getSettings().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(menu);
                add(settings, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        resumeMenu.getResume().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(resumeMenu);
                add(menuBar, BorderLayout.NORTH);
                add(canvas, BorderLayout.CENTER);
                add(statusBar, BorderLayout.SOUTH);
                revalidate();
                repaint();

                //set difficulty if it got modified by settings object
                //also checks if it's single player mode, otherwise it's pointless
                if (canvas.getDifficulty() != settings.getSelectedDifficulty() && !multiplayer)
                    canvas.setDifficulty(settings.getSelectedDifficulty());

                canvas.togglePause(); //resume game
            }
        });

        resumeMenu.getSettings().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(resumeMenu);
                add(settings, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        settings.getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(settings);

                // If game is On (thread is alive), back button brings you to resumeMenu
                // otherwise to the main initial menu
                if (t.isAlive())
                    add(resumeMenu, BorderLayout.CENTER);
                else
                    add(menu, BorderLayout.CENTER);

                revalidate();
                repaint();
            }
        });

        menuBar.getPause().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                canvas.togglePause(); //pauses the game

                remove(menuBar);
                remove(canvas);
                remove(statusBar);

                add(resumeMenu);
                revalidate();
                repaint();
            }
        });

        menuBar.getMusicSwitch().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //change to mute icon
                if (menuBar.getMusicSwitch().getIcon() == MenuPanel.musicOnIcon)
                    menuBar.getMusicSwitch().setIcon(MenuPanel.musicOffIcon);
                else
                    menuBar.getMusicSwitch().setIcon(MenuPanel.musicOnIcon);
            }
        });

        setVisible(true);
    }

    @Override
    public void run() {
        scoreLeft = 0; scoreRight = 0;

        canvas.setAuto(!multiplayer); //set multiplayer

        //if it's single play (vs cpu), I need to specify a difficulty
        //and remove player 2 keybindings
        if (!multiplayer) {
            canvas.getActionMap().remove("COMMA");
            canvas.getActionMap().remove("PERIOD");

            canvas.setDifficulty(settings.getSelectedDifficulty());
        }

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
