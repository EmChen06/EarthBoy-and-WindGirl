import java.awt.image.BufferedImage;

public class Character {

    BufferedImage img;
    int x, y;
    final int w = 20, h = 40;
    double xx, yy, vx, vy;
    boolean isJump;

    Character(int startX, int startY, BufferedImage image, boolean jump) {
        this.img = image;
        this.x = startX;
        this.y = startY;
        this.isJump = jump;
        vx = 0;
        vy = 0;
    }

    void move() {
    }

    void setX(int newX) {
        this.x = newX;
    }

    void setY(int newY) {
        this.y = newY;
    }

    void setJump(boolean jump) {
        this.isJump = jump;
    }

    boolean getJump() {
        return this.isJump;
    }

}