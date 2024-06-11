import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Platform extends Rectangle{
    BufferedImage image;
    double xx, yy, vx, vy;
    int lBound = -1, tBound = -1, rBound = -1, bBound =-1;

    Platform(int x, int y, int width, int height){ //TODO: add BufferedImage img into constructor
        super(x,y,width,height);
        this.xx = this.x;
        this.yy = this.y;
        // this.image = img;
    }

    Platform(int x, int y, int width, int height, int vx, int vy, int leftBound, int topBound, int rightBound, int botBound){
        super(x,y,width,height);
        this.xx = x;
        this.yy = y;

        this.vx = vx;
        this.vy = vy;

        this.lBound = leftBound;
        this.rBound = rightBound;
        this.tBound = topBound;
        this.bBound = botBound;

        
    }

    void move() {
        this.xx += vx;
        this.yy -= vy;
        this.x = (int)this.xx;
        this.y = (int)this.yy;
        if (this.tBound != -1){
            this.checkBoundary();
        }
        this.checkCollision(EarthBoyWindGirl.interactableList);
        this.checkPlayerCollisino(EarthBoyWindGirl.earthBoy);
        this.checkPlayerCollisino(EarthBoyWindGirl.windGirl);

    }

    public void move(double xSpeed, double ySpeed){
        this.xx += xSpeed;
        this.yy -= ySpeed;
        this.x = (int)this.xx;
        this.y = (int)this.yy;
        if (this.tBound != -1){
            this.checkBoundary();
        }
        this.checkCollision(EarthBoyWindGirl.interactableList);
        this.checkPlayerCollisino(EarthBoyWindGirl.earthBoy);
        this.checkPlayerCollisino(EarthBoyWindGirl.windGirl);
    }

    private void checkBoundary() {
            if (this.x < this.lBound){
                this.setX(lBound);
            }
            if (this.x + this.width > this.rBound){
                this.setX(this.rBound - this.width);
            }
            if(this.y < this.tBound){
                this.setY(this.tBound);
            }
            if(this.y + this.height > this.bBound){
                this.setY(this.bBound - this.height);
            }
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
            checkPlatformCollision(p);
        }
    }

    void checkPlatformCollision(Platform p){
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

    void checkPlayerCollisino(Character p){
        if (this.x < p.x + p.w && this.x + this.width > p.x && this.y + this.height >= p.y && vy < 0 && this.y + this.height <= p.y + p.h && this.y + this.height + this.vy <= p.y) {
            this.setY(p.y - this.height);
            this.setVY(0);
            // System.out.println("COLLIDING");
        }
        if (this.y < p.y + p.h && this.x + this.width > p.x && this.x < p.x + p.w && vy > 0 && this.y + this.height > p.y + p.h) {
            this.setY(p.y + p.h);
            this.setVY(0);
            // System.out.println("COLLIDING");                
        }

        if (this.x + this.width > p.x && this.x < p.x && this.y < p.y + p.h && this.y + this.height > p.y) {
            this.setX(p.x - this.width);
            //this.setVX(0);
            //System.out.println("COLLIDING");

        }
        if (this.x < p.x + p.w && this.x + this.width > p.x + p.w && this.y < p.y + p.h && this.y + this.height > p.y) {
            this.setX(p.x + p.w);
            //this.setVX(0);
            //System.out.println("COLLIDING");                
        }

    }
}
