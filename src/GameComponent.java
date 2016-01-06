import java.awt.*;

/**
 * Created by gval on 06/01/16.
 */
public abstract class GameComponent {
    private int width, height;
    private int speed;
    private Color color;

    GameComponent() {
        width = 0;
        height = 0;
        speed = 1;
        color = null;
    }

    GameComponent(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getSpeed() {
        return speed;
    }
    public Color getColor() {
        return color;
    }

    public void setSpeed(int speed) {
        this.speed = Math.abs(speed);
    }
}

class Ball extends GameComponent {
    private int xShifter = 0, yShifter = 0;

    Ball() {
        this(20, 20, Color.WHITE);
    }

    Ball(int size, Color c) {
        this(size, size, c);
    }

    Ball(int width, int height, Color c) {
        super(width, height, c);
        setSpeed(5);
    }

    public void setShifters(int x, int y) {
        xShifter = x;
        yShifter = y;
    }

    public int getXshifter() {
        return xShifter;
    }

    public int getYshifter() {
        return yShifter;
    }
}

class Paddle extends GameComponent {
    private int positionShifter = 0;

    Paddle() {
        this(25, 100, Color.WHITE);
    }

    Paddle(int width, int height, Color c) {
        super(width, height, c);
        setSpeed(15);
    }

    public int getPositionShifter() {
        return positionShifter;
    }

    public void setPositionShifter(int value) {
        positionShifter = value;
    }
}