/**
 * Created by gval on 12/09/15.
 */
import java.awt.*;
import javax.swing.*;
import static java.lang.Math.random;

enum Direction {UP, DOWN}
enum Player {RIGHT, LEFT}

public class GamePanel extends JPanel {
    private enum Pos {LEFT, RIGHT, CENTER} //position on canvas

    private enum PaddleCollision {
        TOP(0), CENTRAL(1), BOTTOM(2);
        private int index;
        PaddleCollision(int i) {
            index = i;
        }
        public int getIndex() {
            return index;
        }
    }

    private int w_pos, h_pos;

    //Gamecomponents creation
    private Ball ball;
    private Paddle leftPaddle, rightPaddle;

    //Game related elements
    private Player whoScored;
    private int tickCount = 0;

    //Collision related components
    private int[][] newDirections = new int[][] {
            { 0, 1, 2 },
            { -1, 0, 1},
            {-2, -1, 0}
    };

    private boolean collisionsDisabled = false;

    GamePanel() {
        ball = new Ball();
        leftPaddle = new Paddle();
        rightPaddle = new Paddle();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        //paint a line and a circle in the middle
        g.setColor(Color.GRAY);
        g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
        g.drawOval(getWidth()/2-40, getHeight()/2-40, 80, 80);

        //paint first and second rectangles
        setCoordinatesToDraw(leftPaddle, Pos.LEFT);
        drawPaddle(leftPaddle, g);
        setCoordinatesToDraw(rightPaddle, Pos.RIGHT);
        drawPaddle(rightPaddle, g);

        //paint ball in the center
        setCoordinatesToDraw(ball, Pos.CENTER);
        drawBall(ball, g);
    }

    public void play() {
        boolean gameIsOn = true;

        //Start the ball at a fixed direction
        ball.changeMovement(-2, -1);

        //Start the game
        while (gameIsOn) {
            gameIsOn = moveBall(ball);
            automaticMove(leftPaddle, true);

            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCoordinatesToDraw(GameComponent gc, Pos p) {
        switch (p) {
            case LEFT:
                w_pos = 0;
                h_pos = (getHeight() / 2) - (gc.getHeight() / 2);
                break;
            case RIGHT:
                w_pos = (getWidth() - gc.getWidth());
                h_pos = (getHeight() / 2) - (gc.getHeight() / 2);
                break;
            case CENTER:
                w_pos = (getWidth() / 2) - (gc.getWidth() / 2);
                h_pos = (getHeight() / 2) - (gc.getHeight() / 2);
                break;
            default: //should never happen
                w_pos = 0;
                h_pos = 0;
                break;
        }
    }

    private void drawPaddle(Paddle p, Graphics g) {
        g.setColor(p.getColor());
        g.fillRect(w_pos, h_pos+p.getPositionShifter(),
                    p.getWidth(), p.getHeight());
    }

    private void drawBall(Ball b, Graphics g) {
        g.setColor(b.getColor());
        g.fillOval(w_pos+b.getXshifter(), h_pos+b.getYshifter(),
                b.getWidth(), b.getHeight());
    }

    //returns false if it can't move
    public boolean movePaddle(Paddle p, Direction dir) {
        boolean upwardsDirection = (dir == Direction.UP);

        if (p.getPositionShifter() <= -(getHeight()/2) + p.getHeight()/2 + p.getSpeed()
                && upwardsDirection)
            return false;
        if (p.getPositionShifter() >= getHeight()/2 - p.getHeight()/2 - p.getSpeed()
                && !upwardsDirection)
            return false;

        if (upwardsDirection)
            p.setPositionShifter(p.getPositionShifter() - p.getSpeed());
        else
            p.setPositionShifter(p.getPositionShifter() + p.getSpeed());

        return true;
    }

    public void automaticMove(Paddle p, boolean b) {
        if (!b) return;

        if (p.getPositionShifter() > ball.getYshifter())
            movePaddle(p, Direction.UP);
        if (p.getPositionShifter() < ball.getYshifter())
            movePaddle(p, Direction.DOWN);
    }

    //returns false when game ends
    public boolean moveBall(Ball b) {
        int newX = b.getXshifter() + b.getxMov()*b.getSpeed();
        int newY = b.getYshifter() + b.getyMov()*b.getSpeed();

        //helper variables (to make if statements more understandable)
        int centralHeight = this.getHeight() / 2;
        int centralWidth = this.getWidth() / 2;

        int ballPositionY = centralHeight + newY;
        int ballPositionX = centralWidth + newX;

        // vertical constrains and collision -> changes ball direction
        if ((ballPositionY - (b.getHeight()/2) < 0) || (ballPositionY + (b.getHeight()/2) > getHeight())) {
            b.changeMovement(b.getxMov(), -b.getyMov());
            newY = b.getYshifter() + b.getyMov() * b.getSpeed();
        }


        if (!collisionsDisabled) {

            // horizontal constrains and collision (on leftPaddle)
            if (ballPositionX - (b.getWidth() / 2) < leftPaddle.getWidth()) {
                if (((ballPositionY <= centralHeight + leftPaddle.getPositionShifter() + leftPaddle.getHeight() / 2) &&
                        (ballPositionY >= centralHeight + leftPaddle.getPositionShifter() - leftPaddle.getHeight() / 2))) {
                    b.changeMovement(-b.getxMov(), vDirectionOnCollision(leftPaddle, b));
                    newX = b.getXshifter() + b.getxMov() * b.getSpeed();
                    tickCount++;
                    System.out.println("tick: " + tickCount + " speed: " + ball.getSpeed());
                } else {
                    collisionsDisabled = true;
                    whoScored = Player.RIGHT;
                }
            }

            // horinzontal constrains and collision (on rightPaddle)
            if (ballPositionX + (b.getWidth() / 2) > getWidth() - rightPaddle.getWidth()) {
                if (((ballPositionY <= centralHeight + rightPaddle.getPositionShifter() + rightPaddle.getHeight() / 2) &&
                        (ballPositionY >= centralHeight + rightPaddle.getPositionShifter() - rightPaddle.getHeight() / 2))) {
                    b.changeMovement(-b.getxMov(), vDirectionOnCollision(rightPaddle, b));
                    newX = b.getXshifter() + b.getxMov() * b.getSpeed();
                    tickCount++;
                    System.out.println("tick: " + tickCount + " speed: " + ball.getSpeed());
                } else {
                    collisionsDisabled = true;
                    whoScored = Player.LEFT;
                }
            }
        }
        else { //if collisions have been disabled, check for the end of the game
            if (b.getXshifter() + centralWidth <= 0 || b.getXshifter() + centralWidth >= getWidth())
                return false;
        }

        //increases speed every 5 ticks by 1
        //something really weird happens if I put (tickCount % 5 == 0) in the condition
        if (tickCount == 5) {
            ball.setSpeed(ball.getSpeed() + 1);
            tickCount = 0;
        }

        b.setShifters(newX, newY);
        return true;
    }

    //returns a PaddleCollision value (0, 1, 2) according to where the ball collides

    private PaddleCollision posOnCollision(Paddle p, Ball b) {
        int ballVerticalPos = getHeight()/2 + b.getYshifter();

        int PaddlePart = p.getHeight() / 3;
        int Y0 = getHeight() / 2 + p.getPositionShifter() - p.getHeight()/2 + PaddlePart;
        int Y1 = Y0 + PaddlePart;

        PaddleCollision position = PaddleCollision.CENTRAL;

        if (ballVerticalPos <= Y0)
            position = PaddleCollision.TOP;
        if (ballVerticalPos >= Y1)
            position = PaddleCollision.BOTTOM;

        return position;
    }

    private int vDirectionOnCollision(Paddle p, Ball b) {
        PaddleCollision position = posOnCollision(p, b);
        int oldDirection = b.getyMov();
        int index;

        if (oldDirection > 0)
            index = 0;
        else if (oldDirection == 0)
            index = 1;
        else
            index = 2;

        return newDirections[index][position.getIndex()];
    }

    public void countDown(int seconds) {
        for (int i=seconds; i > 0; i--) {
            System.out.print(i + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset() {
        collisionsDisabled = false;
        tickCount = 0;
        leftPaddle.setPositionShifter(0);
        rightPaddle.setPositionShifter(0);
        ball.setShifters(0, 0);
        ball.setSpeed(2);
    }

    public Player getWhoScored() {
        return whoScored;
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }
}