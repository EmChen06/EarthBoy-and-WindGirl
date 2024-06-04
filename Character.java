import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character {

    BufferedImage img;
    int x, y;
    final int w = 20, h = 40;
    double xx, yy, vx, vy;
    boolean isJump, isDead;
    double gravity = 0.98;
    boolean preparedJump = true;

    Character(int startX, int startY, BufferedImage image, boolean jump, boolean dead) {
        this.img = image;
        this.x = startX;
        this.xx = this.x;
        this.y = startY;
        this.yy = this.y;
        this.isJump = jump;
        this.isDead = dead;
        vx = 0;
        vy = 0;
    }

    void move() {
        this.xx  += this.vx;
        this.yy -= this.vy;
        this.x = (int)this.xx;
        this.y = (int)this.yy;
        this.checkCollision(EarthBoyWindGirl.platforms);
        
        if (this.isJump){
            this.setVY(this.vy -= gravity);
        }
    }

    void setDead(boolean newDead) {
        this.isDead = newDead;
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

    void setPreparedJump (boolean prepared){
        this.preparedJump = prepared;
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
        boolean jumping = true;
        for (Platform p : platforms){
            if (this.x < p.x+p.width && this.x  + this.w> p.x && this.y + this.h>= p.y && vy < 0 && this.y + this.h <= p.y+p.height && this.y + this.h + this.vy <= p.y) {
                this.setY(p.y - this.h);
                this.setVY(0);
                // System.out.println("COLLIDING");
                this.isJump = false;
                jumping = false;
            }
            if (this.x + this.w > p.x && this.x < p.x && this.y < p.y + p.height && this.y + this.h > p.y){
                this.setX(p.x - this.w);
                //this.setVX(0);
                //System.out.println("COLLIDING");

            }
            if (this.x < p.x + p.width && this.x + this.w > p.x + p.width && this.y < p.y + p.height && this.y + this.h > p.y){
                this.setX(p.x + p.width);
                //this.setVX(0);
                //System.out.println("COLLIDING");                
            }

            if (this.y < p.y + p.height && this.x + this.w > p.x && this.x < p.x + p.width && vy > 0 && this.y + this.h > p.y + p.height){
                this.setY(p.y + p.height);
                this.setVY(0);
                // System.out.println("COLLIDING");                
            }

        this.isJump = jumping;
        }
    }
}