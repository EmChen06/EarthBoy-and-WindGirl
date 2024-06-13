import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character{

    //initialize variables
    BufferedImage img;
    int x, y;
    final int w = 20, h = 40;
    double xx, yy, vx, vy;
    boolean isJump, isDead;
    double gravity = 0.98;
    boolean preparedJump = true;
    String name = "";

    /**
     * Initialize a character
     * @param startX    the character's starting x
     * @param startY    the character's staring y
     * @param image     the image for the character
     * @param jump      Boolean for checking jump
     * @param dead      Boolean for checking if they're dead
     */
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

    /**
     * Letting the character move
     */
    void move() {
        this.xx += this.vx;
        this.yy -= this.vy;
        this.x = (int) this.xx;
        this.y = (int) this.yy;
        this.checkCollision(EarthBoyWindGirl.platforms);

        if (this.isJump) {
            this.setVY(this.vy -= gravity);
        }
    }

    /**
     * checks if the character is dead
     * @param newDead   Boolean that determines whether they are dead or not
     */
    void setDead(boolean newDead) {
        this.isDead = newDead;
    }

    /**
     * Sets the new x value for character
     * @param newX      value for the new x 
     */
    void setX(int newX) {
        this.xx = newX;
        this.x = newX;
    }

    /**
     * Sets the new y value for character
     * @param newY      value for the new y
     */
    void setY(int newY) {
        this.yy = newY;
        this.y = newY;
    }

    /**
     * sets the jump to be either true or false
     * @param jump      boolean determining whether they are jumping or not
     */
    void setJump(boolean jump) {
        this.isJump = jump;
    }

    /**
     * sets preparedJump to be true or flase
     * @param prepared boolean determining whether they are preparing their jump
     */
    void setPreparedJump(boolean prepared) {
        this.preparedJump = prepared;
    }

    /**
     * Sets the x speed
     * @param speedX    value for x speed
     */
    void setVX(double speedX) {
        this.vx = speedX;
    }
    
    /**
     * Sets the y speed
     * @param speedY    value for y speed
     */
    void setVY(double speedY) {
        this.vy = speedY;
    }

    /**
     * checks if they are jumping or not
     * @return  returns a boolean determining if they are jumping (true) or not (false)
     */
    boolean getJump() {
        return this.isJump;
    }

    /**
     * Checks collisions against platforms
     * @param platforms     an arraylist of platforms to check against
     */
    void checkCollision(ArrayList<Platform> platforms) {
        boolean jumping = true;
        for (Platform p : platforms) {
            if (this.x + this.w > p.x && this.x < p.x && this.y < p.y + p.height && this.y + this.h > p.y) {
                this.setX(p.x - this.w);
                //this.setVX(0);
                //System.out.println("COLLIDING");

            }
            if (this.x < p.x + p.width && this.x + this.w > p.x + p.width && this.y < p.y + p.height && this.y + this.h > p.y) {
                this.setX(p.x + p.width);
                //this.setVX(0);
                //System.out.println("COLLIDING");                
            }
            if (this.x < p.x + p.width && this.x + this.w > p.x && this.y + this.h >= p.y && vy < 0 && this.y + this.h <= p.y + p.height && this.y + this.h + this.vy <= p.y) {
                this.setY(p.y - this.h);
                this.setVY(0);
                // System.out.println("COLLIDING");
                this.isJump = false;
                jumping = false;
            }
            if (this.y < p.y + p.height && this.x + this.w > p.x && this.x < p.x + p.width && vy > 0 && this.y + this.h > p.y + p.height) {
                this.setY(p.y + p.height);
                this.setVY(0);
                // System.out.println("COLLIDING");                
            }

            

            this.isJump = jumping;
        }
    }
}
