import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Platform extends Rectangle{
    BufferedImage image;
    double xx, yy, vx, vy;

    Platform(int x, int y, int width, int height){ //TODO: add BufferedImage img into constructor
        super(x,y,width,height);
        this.xx = this.x;
        this.yy = this.y;
        // this.image = img;
    }

    void move() {
        this.xx += vx;
        this.yy -= vy;
        this.x = (int)this.xx;
        this.y = (int)this.yy;
        this.checkCollision(EarthBoyWindGirl.platforms);
    }

    void setVX(double speedX) {
        this.vx = speedX;
    }

    void setVY(double speedY) {
        this.vy = speedY;
    }

    void setY(int y){
        this.yy = y;
        this.y = y;
    }

    void setX(int x){
        this.xx = x;
        this.x = x;
    }

    void checkCollision(ArrayList<Platform> platforms){
        for (Platform p : platforms){
            if (this.x < p.x+p.width && this.x  + this.width> p.x && this.y + this.height>= p.y && vy < 0 && this.y + this.height <= p.y+p.height && this.y + this.height + this.vy <= p.y) {
                this.setY(p.y - this.height);
                this.setVY(0);
                System.out.println("COLLIDING 1");
            }
            if (this.y < p.y + p.height && this.x + this.width > p.x && this.x < p.x + p.width && vy > 0 && this.y + this.height > p.y + p.height){
                this.setY(p.y + p.height);
                this.setVY(0);
                System.out.println("COLLIDING 4");                
            }
            if (this.x + this.width > p.x && this.x < p.x && this.y < p.y + p.height && this.y + this.height > p.y){
                this.setX(p.x - this.width);
                this.setVX(0);
                System.out.println("COLLIDING 2");

            }
            if (this.x < p.x + p.width && this.x + this.width > p.x + p.width && this.y < p.y + p.height && this.y + this.height > p.y){
                this.setX(p.x + p.width);
                this.setVX(0);
            }

           
        }
    }
}
