import java.awt.image.BufferedImage;

public abstract class Interactable extends Platform{

    BufferedImage img;
    boolean interacted = false;

    Interactable(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h);
        this.img = image;
    }

    void checkCollision(Character c){
        // System.out.println(c.name);
        if (c.x < this.x+this.width && c.x  + c.w> this.x && c.y + c.h>= this.y && c.vy < 0 && c.y + c.h <= this.y+this.height && c.y + c.h + c.vy <= this.y) {
                interacted = true;

        }
        else if (c.y < this.y + this.height && c.x + c.w > this.x && c.x < this.x + this.width && c.vy > 0 && c.y + c.h > this.y + this.height){
                interacted = true;
        }

        else if (c.x + c.w > this.x && c.x < this.x && c.y < this.y + this.height && c.y + c.h > this.y){
                interacted = true;
        }
        else if (c.x < this.x + this.width && c.x + c.w > this.x + this.width && c.y < this.y + this.height && c.y + c.h > this.y){
                interacted = true;
        }
        else if (c.x >= this.x && c.x + c.w <= this.x + this.width && c.y >= this.y && c.y + c.h <= this.y + this.height){
                interacted = true;
        }

        else {
            interacted = false;
        }

        if (interacted){
            onInteraction(c);
        }

        if (! interacted){
            onLeave(c);
        }
    }

    abstract void onInteraction(Character c);

    abstract void onLeave(Character c);
}