import java.awt.image.BufferedImage;

public class WindGirl extends Character{
    boolean isDoubleJump = false;
    WindGirl(int startX, int startY, BufferedImage image, boolean jump, boolean dead) {
        super(startX, startY, image, jump, dead);
    }

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

    void setDoubleJump(boolean doubleJump) {
        this.isDoubleJump = doubleJump;
    }
    

}