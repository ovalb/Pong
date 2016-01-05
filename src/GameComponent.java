import java.awt.*;

/**
 * Created by gval on 06/01/16.
 */
public interface GameComponent {
    public int getWidth();
    public int getHeight();
}

class Ball implements GameComponent {
    private int bWidth, bHeight;
    private Color color;

    Ball() {
        bWidth = 20;
        bHeight = 20;
        color = Color.WHITE;
    }

    Ball(int size, Color c) {
        bWidth = size;
        bHeight = size;
        color = c;
    }

    Ball(int width, int height, Color c) {
        bWidth = width;
        bHeight = height;
        color = c;
    }

    public void draw(int x, int y, Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, bWidth, bHeight);
    }

    public int getWidth() {
        return bWidth;
    }

    public int getHeight() {
        return bHeight;
    }
}

class Paddle implements GameComponent {
    private int pWidth, pHeight;
    private Color color;

    Paddle() {
        pWidth = 25;
        pHeight = 100;
        color = Color.WHITE;
    }

    Paddle(int width, int height, Color c) {
        pWidth = width;
        pHeight = height;
        color = c;
    }

    public void draw(int x, int y, Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, pWidth, pHeight);
    }

    public int getWidth() {
        return pWidth;
    }

    public int getHeight() {
        return pHeight;
    }
}