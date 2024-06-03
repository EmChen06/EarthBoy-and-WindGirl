import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
        this.xx  += this.vx;
        this.yy += this.vy;
        this.x = (int)this.xx;
        this.y = (int) this.yy;
    }

    void setX(int newX) {
        this.xx = newX;
        this.x = newX;
    }

    void setY(int newY) {
        this.yy = newY;
        this.y = newY;
    }

    void setJump(boolean jump) {
        this.isJump = jump;
    }

    void setVX(double speedX) {
        this.vx = speedX;
    }

    void setVY(double speedY) {
        this.vy = speedY;
    }

    boolean getJump() {
        return this.isJump;
    }

    void checkCollision(ArrayList<Platform> platforms){
        for (Platform p : platforms){
            if (this.x < p.x+p.width && this.x  + this.w> p.x && this.y + this.h >= p.y && vy <= 0 && this.y + this.h <= p.y+p.height) {
                this.y = p.y - this.h;
                this.setVY(0);
                System.out.println("COLLIDING");
                isJump = false;
            }
            if (this.x + this.w > p.x && this.y < p.y + p.height && this.y + this.h > p.y){
                this.x = p.x - this.w;
            }
            if (this.x < p.x + p.width){
                
            }


        }
    }
}