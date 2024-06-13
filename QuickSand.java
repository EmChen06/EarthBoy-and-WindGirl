import java.awt.image.BufferedImage;

public class QuickSand extends Interactable{

    /**
     * Constructor for quicksand
     * @param x top-left x-coordinate
     * @param y top-left y-coordinate
     * @param w width of quicksand
     * @param h height of quicksand
     * @param image image of quicksand
     */
    QuickSand(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h, image);
    }

    @Override
    /**
     * @param c the player interacting with quickSand
     * If the character is Windgirl, kill her
     */
    void onInteraction(Character c) {
        if (c.name.equals("windGirl")){
            c.isDead = true;
            //System.out.println("DIE DIE");
        }        
    }

    @Override
    void onLeave(Character c) {        
    }

}