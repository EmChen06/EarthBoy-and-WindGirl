import java.awt.image.BufferedImage;

public class EarthBoy extends Character{

    boolean isBuild;

    EarthBoy(int startX, int startY, BufferedImage image, boolean jump, boolean build) {
        super(startX, startY, image, jump);
        this.isBuild = build;
    }

    void enterBuildMode() {
        this.isBuild = true;
    }

    void leaveBuildMode() {
        this.isBuild = false;
    }

}