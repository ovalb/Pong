/**
 * Created by gval on 12/09/15.
 */
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    final private int RECT_W = 25;
    final private int RECT_H = 100;
    final private int BALL_SIZE = 30;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //height and width positions for rectangles
        final int HEIGHT_POS= (getHeight() / 2) - (RECT_H/2);
        final int WIDTH_POS = getWidth() - RECT_W;

        //paint first and second rectangles
        g.setColor(Color.WHITE);
        g.fillRect(0, HEIGHT_POS, RECT_W, RECT_H);
        g.fillRect(WIDTH_POS, HEIGHT_POS, RECT_W, RECT_H);

        //paint ball in the center
        g.fillOval(getWidth()/2-(BALL_SIZE/2) , getHeight()/2-(BALL_SIZE/2),
                    BALL_SIZE, BALL_SIZE);
    }
}
