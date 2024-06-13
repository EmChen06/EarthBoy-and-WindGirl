/**
 * PressurePlate.java
 * Emilee Chen, David Hang, Hansheng Liu
 * June 13th, 2024
 * Pressure plate class which move a platform once stand on it
 */

import java.awt.image.BufferedImage;

public class PressurePlate extends Interactable{

    Platform p;

    /**
     * Constructor for platform class
     * @param x top-left x-coordinate
     * @param y top-right y-coordinate
     * @param w width of pressureplate
     * @param h height of pressureplate
     * @param image image of pressureplate
     * @param p platform that the pressure plate control
     */
    PressurePlate(int x, int y, int w, int h, BufferedImage image, Platform p) {
        super(x, y, w, h, image);
        this.p = p;
    }

    @Override
    /**
     * when interacting with pressureplate, move the corresponding platform
     * @param c the character standing on the pressure plate
     */
    void onInteraction(Character c) {
        //System.out.println("enter");
        p.move();
    }

    @Override
    /**
     * if player leave pressureplate, platform moves back
     * @param c the character leaving the pressure plate
     */
    void onLeave(Character c) {       
        
        p.move(p.vx * -1, p.vy * -1);
    }
}
