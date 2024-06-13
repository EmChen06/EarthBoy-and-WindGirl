/**
 * Door.java
 * Emilee Chen, David Hang, Hansheng Liu
 * June 13th, 2024
 * Door class which define the winning condition
 */

import java.awt.image.BufferedImage;

public class Door extends Interactable {

    boolean charDone = false;

    /**
     * Constructor class for door
     * @param x top-left x-coordinate
     * @param y top-left y-coordinate
     * @param w width of door
     * @param h height of door
     * @param image image of door  
     */
    Door(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h, image);
    }

    @Override
    /**
     * On interaction, the player is said to be done
     * @param c the character interacting with the door
     */
    public void onInteraction(Character character) {
        this.charDone = true;
    }

    @Override
    /**
     * If the player leave the door, they are no longer done
     * @param c the character leaving the door
     */
    void onLeave(Character c) {
        this.charDone = false;        
    }
}