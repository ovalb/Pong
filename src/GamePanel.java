/**
 * Created by gval on 12/09/15.
 */
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    Graphics g;
    final private int RECT_W = 25;
    final private int RECT_H = 100;
    final private int BALL_SIZE = 30;
    final static int SHIFT_SPEED = 10;

    private int RPaneShifter = 0;
    private int LPaneShifter = 0;

    public void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);

        //height and width positions for rectangles
        final int HEIGHT_POS= (getHeight() / 2) - (RECT_H/2);
        final int WIDTH_POS = getWidth() - RECT_W;

        //paint first and second rectangles
        g.setColor(Color.WHITE);
        g.fillRect(0, HEIGHT_POS+RPaneShifter, RECT_W, RECT_H);
        g.fillRect(WIDTH_POS, HEIGHT_POS+LPaneShifter, RECT_W, RECT_H);

        //paint ball in the center
        g.fillOval(getWidth() / 2 - (BALL_SIZE / 2), getHeight() / 2 - (BALL_SIZE / 2),
                BALL_SIZE, BALL_SIZE);
    }

    //something is wrong here...
    public boolean canMove(int paneShifter, boolean upwardsDirection) {
        if (paneShifter <= -(getHeight()/2) + RECT_H/2 + SHIFT_SPEED && upwardsDirection)
            return false;
        //can't move down
        if (paneShifter >= getHeight()/2 - RECT_H/2 - SHIFT_SPEED && !upwardsDirection)
            return false;

        return true;
    }

    public void setLPaneShifter(int LPaneShifter) {
        this.LPaneShifter = LPaneShifter;
    }

    public void setRPaneShifter(int RPaneShifter) {
        this.RPaneShifter = RPaneShifter;
    }

    public int getRPaneShifter() {
        return RPaneShifter;
    }

    public int getLPaneShifter() {
        return LPaneShifter;
    }
}
