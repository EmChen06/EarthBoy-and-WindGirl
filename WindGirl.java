import java.awt.image.BufferedImage;

public class WindGirl extends Character{
   
    //initialize variables
    boolean isDoubleJump = false;
    
    /**
     * Creates a wind girl object
     * @param startX    the starting x position of Windgirl
     * @param startY    the startion y position of Windgirl
     * @param image     Windgirl's Image
     * @param jump      Checking if she is jumping
     * @param dead      Checking if she is dead
     */
    WindGirl(int startX, int startY, BufferedImage image, boolean jump, boolean dead) {
        super(startX, startY, image, jump, dead);
        this.name = "windGirl";
    }

    /**
     * Enables windgirl's drifting mechanic as well as her regular movement
     */
    @Override
    void move() {
        if (this.vy < 0 && !this.preparedJump){
            this.vy -= 2*this.gravity;
            this.gravity = 0;
        }
        else{
            this.gravity = 0.98;
        }
        super.move();
    }

    /**
     * Checks if she is double jumping or not
     * @param doubleJump the boolean if doublejump is enabled or not
     */
    void setDoubleJump(boolean doubleJump) {
        this.isDoubleJump = doubleJump;
    }
    

}