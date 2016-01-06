/**
 * Created by gval on 12/09/15.
 */
import java.awt.*;
import javax.swing.*;
import static java.lang.Math.*;

enum Direction {UP, DOWN};

public class GamePanel extends JPanel {
    private enum Pos {LEFT, RIGHT, CENTER}; //position on canvas

    private int w_pos, h_pos;

    private Ball ball = new Ball();

    private Paddle leftPaddle;
    private Paddle rightPaddle;

    GamePanel() {
        leftPaddle = new Paddle();
        rightPaddle = new Paddle();
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

        changeBallDirection(ball, 0);
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
        g.fillOval(w_pos+1, h_pos, b.getWidth(), b.getHeight());
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

    public void changeBallDirection(Ball b, double dir) {
        //this method should modify the x / y position shifter coordinates!
        //using angles -> convert them into x and y shifts
//        System.out.println("Sin of " + dir + " is: " + sin(dir));
//        System.out.println("Cos of " + dir + " is: " + cos(dir));
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }
}


