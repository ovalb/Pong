/**
 * Created by gval on 12/09/15.
 */
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    final private int RECT_W = 25;
    final private int RECT_H = 100;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        final int HEIGHT_POS= (getHeight() / 2) - (RECT_H/2);
        final int WIDTH_POS = getWidth() - RECT_W;

        //paint first rectangle
        g.setColor(Color.WHITE);
        g.fillRect(0, HEIGHT_POS, RECT_W, RECT_H);
        g.fillRect(WIDTH_POS, HEIGHT_POS, RECT_W, RECT_H);
    }
}
