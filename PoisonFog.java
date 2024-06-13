/**
 * PoisonFog.java
 * Emilee Chen, David Hang, Hansheng Liu
 * June 13th, 2024
 * Poison fog class which will kill Earthboy on impact
 */

import java.awt.image.BufferedImage;

public class PoisonFog extends Interactable{

    /**
     * Constructor class for poisonFog
     * @param x top left x-coordinate
     * @param y top left y-coordinate
     * @param w width of poison fog
     * @param h height of poison fog
     * @param image image of poison fog
     */
    PoisonFog(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h, image);
    }

    @Override
    /**
     * @param c the character that interacts with the poison fog
     * If the character is Earthboy, he is dead
     */
    void onInteraction(Character c) {
        if (c.name.equals("earthBoy")){
            c.isDead = true;
            //System.out.println("DIE DIE");
        }        
    }

    @Override
    void onLeave(Character c) {        
    }
    

}
