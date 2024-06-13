/**
 * Platform.java
 * Emilee Chen, David Hang, Hansheng Liu
 * June 13th, 2024
 * Platform class
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Platform extends Rectangle{
    BufferedImage image;
    double xx, yy, vx, vy;
    int lBound = -1, tBound = -1, rBound = -1, bBound =-1;

    /**
     * Constructor class for a normal non-moving platform
     * @param x top-left x-coordinate
     * @param y top-left y-coordinate
     * @param width width of the platform
     * @param height    height of the platform
     */
    Platform(int x, int y, int width, int height){
        super(x,y,width,height);
        this.xx = this.x;
        this.yy = this.y;
        // this.image = img;
    }

    /**
     * Constructor for a moving platform
     * @param x top-left x-coordinate
     * @param y top-left y-coordinate
     * @param width width of platform
     * @param height    height of platform
     * @param vx    horizontal speed of platform
     * @param vy    vertical speed of platform
     * @param leftBound the left-most place that the platform can travel
     * @param topBound  the highest place that the platform can travel to
     * @param rightBound    the right-most place that the platform can travel to
     * @param botBound  the lowest place that the platform can travel to
     */
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

    /**
     * Move the platform by changing its x and y based on its speed
     */
    void move() {
        this.xx += vx;
        this.yy -= vy;
        this.x = (int)this.xx;
        this.y = (int)this.yy;
        // check for boundary
        if (this.tBound != -1){
            this.checkBoundary();
        }
        // check for collisions
        this.checkCollision(EarthBoyWindGirl.interactableList);
        this.checkPlayerCollisino(EarthBoyWindGirl.earthBoy);
        this.checkPlayerCollisino(EarthBoyWindGirl.windGirl);

    }

    /**
     * Move the platform based on xSpeed and ySpeed 
     * @param xSpeed    horizontal speed
     * @param ySpeed    vertical speed
     */
    public void move(double xSpeed, double ySpeed){
        this.xx += xSpeed;
        this.yy -= ySpeed;
        this.x = (int)this.xx;
        this.y = (int)this.yy;
        // check collision
        if (this.tBound != -1){
            this.checkBoundary();
        }
        this.checkCollision(EarthBoyWindGirl.interactableList);
        this.checkPlayerCollisino(EarthBoyWindGirl.earthBoy);
        this.checkPlayerCollisino(EarthBoyWindGirl.windGirl);
    }

    /**
     * Check a moving platform's collision with its boundary
     */
    private void checkBoundary() {
            // ensure the platform is always within bound
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
    

    /**
     * set vx to speedX
     * @param speedX    new horizontal speed
     */
    void setVX(double speedX) {
        this.vx = speedX;
    }

    /**
     * set vy to speedY
     * @param speedY    new vertical speed
     */
    void setVY(double speedY) {
        this.vy = speedY;
    }

    /**
     * change y-coordinate to y
     * @param y new y-coordinate
     */
    void setY(int y){
        this.yy = y;
        this.y = y;
    }

    /**
     * change x-coordinate to x
     * @param x new x-coordinate
     */
    void setX(int x){
        this.xx = x;
        this.x = x;
    }

    /**
     * check collision with other platforms
     * @param platforms arrayList of platform to check collision against
     */
    void checkCollision(ArrayList<Platform> platforms){
        for (Platform p : platforms){
            checkPlatformCollision(p);
        }
    }

    /**
     * check collision with one other platform
     * @param p the platform to check collision with
     */
    void checkPlatformCollision(Platform p){
        // ensure that the moving platform cannot travel through other platform
        if (this.x < p.x+p.width && this.x  + this.width> p.x && this.y + this.height>= p.y && vy < 0 && this.y + this.height <= p.y+p.height && this.y + this.height + this.vy <= p.y) {
            this.setY(p.y - this.height);
            //System.out.println("COLLIDING 1");
        }
        if (this.y < p.y + p.height && this.x + this.width > p.x && this.x < p.x + p.width && vy > 0 && this.y + this.height > p.y + p.height){
            this.setY(p.y + p.height);
            //System.out.println("COLLIDING 4");                
        }
        if (this.x + this.width > p.x && this.x < p.x && this.y < p.y + p.height && this.y + this.height > p.y){
            this.setX(p.x - this.width);
            //System.out.println("COLLIDING 2");

        }
        if (this.x < p.x + p.width && this.x + this.width > p.x + p.width && this.y < p.y + p.height && this.y + this.height > p.y){
            this.setX(p.x + p.width);
        }
    }

    /**
     * Check collision against player
     * @param p player to check collision against
     */
    void checkPlayerCollisino(Character p){
        //  Make sure moving platform doesn't clip through players
        if (this.x < p.x + p.w && this.x + this.width > p.x && this.y + this.height >= p.y && vy < 0 && this.y + this.height <= p.y + p.h && this.y + this.height + this.vy <= p.y) {
            this.setY(p.y - this.height);
            // System.out.println("COLLIDING");
        }
        if (this.y < p.y + p.h && this.x + this.width > p.x && this.x < p.x + p.w && vy > 0 && this.y + this.height > p.y + p.h) {
            this.setY(p.y + p.h);
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
