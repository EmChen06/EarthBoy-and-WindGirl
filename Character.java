import java.awt.image.BufferedImage;

public class Character {

    BufferedImage img;
    int x, y
    final int w = 20, h = 40;
    double xx, yy, vx, vy;

    Character(int startX, int startY, BufferedImage image) {
        this.img = image;
        this.x = startX;
        this.y = startY;
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

}