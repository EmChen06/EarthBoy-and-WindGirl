import java.awt.image.BufferedImage;

public class EarthBoy extends Character{

    //initialize variables
    boolean isBuild;
    boolean readyBuild = true;

    /**
     * Creates an Earthboy Object
     * @param startX    the starting x position of Earthboy
     * @param startY    the startion y position of Earthboy
     * @param image     Earthboy's Image
     * @param jump      Checking if he is jumping
     * @param dead      Checking if he is dead
     * @param build     Checking if he is building
     */
    EarthBoy(int startX, int startY, BufferedImage image, boolean jump, boolean dead, boolean build) {
        super(startX, startY, image, jump, dead);
        this.isBuild = build;
        this.name = "earthBoy";
    }

    /**
     * allows Earthboy to enter build mode
     */
    void enterBuildMode() {
        this.isBuild = true;
    }

    /**
     * wont move unless he is not in build mode
     */
    @Override
    void move() {
        if (! this.isBuild){
            super.move();
        } 
    }

    /*
     * Leaves the build mode
     */
    void leaveBuildMode() {
        this.isBuild = false;
    }

    /*
     * Checking if he is in build mode or not
     */
    public void setReadyBuild(boolean readyBuild) {
        this.readyBuild = readyBuild;
    }

}