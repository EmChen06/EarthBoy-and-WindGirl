import java.awt.image.BufferedImage;

public class EarthBoy extends Character{

    boolean isBuild;

    EarthBoy(int startX, int startY, BufferedImage image, boolean jump, boolean dead, boolean build) {
        super(startX, startY, image, jump, dead);
        this.isBuild = build;
    }

    void enterBuildMode() {
        this.isBuild = true;
    }

    //it wont move
    @Override
    void move() {
        if (! this.isBuild){
            super.move();
        } 
    }

    void leaveBuildMode() {
        this.isBuild = false;
    }

}