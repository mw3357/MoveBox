import java.awt.*;

public class Box {
    private Color color;
    private int x;
    private int y;
    private int width;

    public Box(Color color, int x, int y, int width) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
