/**
 * Created by gval on 12/09/15.
 */
import java.awt.*;
import javax.swing.*;
import static java.lang.Math.random;

enum Direction {UP, DOWN}

public class GamePanel extends JPanel implements Runnable {
    private enum Pos {LEFT, RIGHT, CENTER} //position on canvas
    private enum PaddleDivision {
        TOP(0), CENTRAL(1), BOTTOM(2);
        private int index;
        PaddleDivision(int i) {
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

    //Collision related components
    private int[][] newDirections = new int[][] {
            { 0, 1, 2 },
            { -1, 0, 1},
            {-2, -1, 0}
    };

    //Thread related objects
    Thread gameThread;



    GamePanel() {
        ball = new Ball();
        leftPaddle = new Paddle();
        rightPaddle = new Paddle();
        gameThread = new Thread(this, "game_session");
        gameThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

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
//        moveBall(ball);
    }

    @Override
    public void run() {
        boolean gameIsOn = true;

        //countdown!
        System.out.print("Game starting in: ");
        for (int i=5; i > 0; i--) {
            System.out.print(i + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Start the ball at a random direction
        ball.changeMovement((int)(random()*10)%4-2, (int)(random()*10)%4-2);
//        ball.changeMovement(-2, -1);

        while(gameIsOn) {
            gameIsOn = moveBall(ball);
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {e.printStackTrace();}
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

    public boolean moveBall(Ball b) {
        int newX = b.getXshifter() + b.getxMov()*b.getSpeed();
        int newY = b.getYshifter() + b.getyMov()*b.getSpeed();

        // vertical constrains and collision -> changes ball direction
        if (((getHeight() / 2) - (b.getHeight() / 2) + newY < 0) ||
                ((getHeight() / 2) + (b.getHeight() / 2) + newY > getHeight())) {
            b.changeMovement(b.getxMov(), -b.getyMov());
            newY = b.getYshifter() + b.getyMov() * b.getSpeed();
        }

        // horizontal constrains and collision (on leftPaddle)
        if ((getWidth() / 2) - (b.getWidth()/2) + newX < leftPaddle.getWidth()) {
            if ((((getHeight() / 2 + newY) <= getHeight()/2 + leftPaddle.getPositionShifter() + leftPaddle.getHeight() / 2) &&
                    ((getHeight() / 2 + newY) >= getHeight()/2 + leftPaddle.getPositionShifter() - leftPaddle.getHeight() / 2)))
            {
                b.changeMovement(-b.getxMov(), vDirectionOnCollision(leftPaddle, b));
                newX = b.getXshifter() + b.getxMov() * b.getSpeed();
            }
        }

        // horinzontal constrains and collision (on rightPaddle)
        if ((getWidth() / 2) + (b.getWidth()/2) + newX > getWidth() - rightPaddle.getWidth()) {
            if ((((getHeight() / 2 + newY) <= getHeight()/2 + rightPaddle.getPositionShifter() + rightPaddle.getHeight() / 2) &&
                    ((getHeight() / 2 + newY) >= getHeight()/2 + rightPaddle.getPositionShifter() - rightPaddle.getHeight() / 2)))
            {
                b.changeMovement(-b.getxMov(), vDirectionOnCollision(rightPaddle, b));
                newX = b.getXshifter() + b.getxMov() * b.getSpeed();
            }
        }

        b.setShifters(newX, newY);
        return true;
    }

    //returns a PaddleCollision value (0, 1, 2) according to where the ball collides
    private PaddleDivision posOnCollision(Paddle p, Ball b) {
        int ballVerticalPos = getHeight()/2 + b.getYshifter();

        int PaddlePart = p.getHeight() / 3;
        int Y0 = getHeight() / 2 + p.getPositionShifter() - p.getHeight()/2 + PaddlePart;
        int Y1 = Y0 + PaddlePart;

        PaddleDivision position = PaddleDivision.CENTRAL;

        if (ballVerticalPos <= Y0)
            position = PaddleDivision.TOP;
        if (ballVerticalPos >= Y1)
            position = PaddleDivision.BOTTOM;

        return position;
    }

    private int vDirectionOnCollision(Paddle p, Ball b) {
        PaddleDivision position = posOnCollision(p, b);
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

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }
}


