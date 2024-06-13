/**
 * Interactable.java
 * Emilee Chen, David Hang, Hansheng Liu
 * June 13th, 2024
 * Interactable class which is an abstract class for all interactables
 */

import java.awt.image.BufferedImage;

public abstract class Interactable extends Platform{

    BufferedImage img;
    boolean interacted = false;

    /**
     * Constructor for interactables
     * @param x top-left x-coordinate
     * @param y top-left y-coordinate
     * @param w width of interactable
     * @param h height of interactable
     * @param image image of interactable
     */
    Interactable(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h);
        this.img = image;
    }

    /**
     * Check collision with character, if colliding perform onInteraction
     * @param c the character to check collision with
     */
    void checkCollision(Character c){
        interacted = colliding(c);

        if (interacted){
            onInteraction(c);
        }

        if (! interacted){
            onLeave(c);
        }
    }

    /**
     * Check collision with two player, useful for Door class where both player need to be on
     * @param c1    the first player
     * @param c2    the second player
     */
    void checkCollision(Character c1, Character c2){
        interacted = (colliding(c1) || colliding(c2));
        // System.out.println(interacted);
        if (interacted){
            onInteraction(c1);
        }

        if (! interacted){
            onLeave(c1);
        }
    }

    /**
     * Define collision for character and interactable
     * @param c the character to check collision for
     * @return  whether there is a collision
     */
    boolean colliding(Character c){
        
        if (c.x < this.x+this.width && c.x  + c.w> this.x && c.y + c.h>= this.y && c.vy < 0 && c.y + c.h <= this.y+this.height && c.y + c.h + c.vy <= this.y) {
            return true;

    }
    else if (c.y < this.y + this.height && c.x + c.w > this.x && c.x < this.x + this.width && c.vy > 0 && c.y + c.h > this.y + this.height){
            return true;
    }

    else if (c.x + c.w > this.x && c.x < this.x && c.y < this.y + this.height && c.y + c.h > this.y){
        return true;
    }
    else if (c.x < this.x + this.width && c.x + c.w > this.x + this.width && c.y < this.y + this.height && c.y + c.h > this.y){
        return true;
    }
    else if (c.x >= this.x && c.x + c.w <= this.x + this.width && c.y >= this.y && c.y + c.h <= this.y + this.height){
        return true;
    }

    else {
        return false;
    } 
    }

    /**
     * abstract method, what an interactable do on collision
     * @param c the character interacting with the interactable
     */
    abstract void onInteraction(Character c);

    /**
     * abstract method, what an interactable do when player leaves
     * @param c the character interacting with the interactable
     */
    abstract void onLeave(Character c);
}