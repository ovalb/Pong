/**
 * Created by gval on 06/01/16.
 */
package game;
import java.awt.*;

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
        this.speed = speed;
    }
}

class Ball extends GameComponent {
    private final int DEFAULT_SPEED = 2;
    private int xShifter = 0, yShifter = 0;
    private int xMov = 0, yMov = 0;

    Ball() {
        this(20, 20, Color.WHITE);
    }

    Ball(int size, Color c) {
        this(size, size, c);
    }

    Ball(int width, int height, Color c) {
        super(width, height, c);
        setSpeed(DEFAULT_SPEED);
    }

    public void setShifters(int x, int y) {
        xShifter = x;
        yShifter = y;
    }

    public void changeMovement(int xMov, int yMov) {
        if (xMov >= -2 && xMov <= 2)
            this.xMov = xMov;
        else
            this.xMov = 0;

        if (yMov >= -2 && yMov <= 2)
            this.yMov = yMov;
        else
            this.yMov = 0;
    }

    public int getXshifter() {
        return xShifter;
    }

    public int getYshifter() {
        return yShifter;
    }

    public int getxMov() {
        return xMov;
    }

    public int getyMov() {
        return yMov;
    }
}

class Paddle extends GameComponent {
    private final int DEFAULT_SPEED = 15;

    private int positionShifter = 0;

    Paddle() {
        this(25, 100, Color.WHITE);
    }

    Paddle(int width, int height, Color c) {
        super(width, height, c);
        setSpeed(DEFAULT_SPEED);
    }

    public int getPositionShifter() {
        return positionShifter;
    }

    public void setPositionShifter(int value) {
        positionShifter = value;
    }
}