import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public abstract class Interactable extends Rectangle{

    BufferedImage img;

    Interactable(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h);
        this.img = image;
    }

    void checkCollision(Character c){
        System.out.println(c.name);
        if (c.x < this.x+this.width && c.x  + c.w> this.x && c.y + c.h>= this.y && c.vy < 0 && c.y + c.h <= this.y+this.height && c.y + c.h + c.vy <= this.y) {
            onInteraction(c);
        }
        if (c.y < this.y + this.height && c.x + c.w > this.x && c.x < this.x + this.width && c.vy > 0 && c.y + c.h > this.y + this.height){
           onInteraction(c);                
        }

        if (c.x + c.w > this.x && c.x < this.x && c.y < this.y + this.height && c.y + c.h > this.y){
            onInteraction(c);

        }
        if (c.x < this.x + this.width && c.x + c.w > this.x + this.width && c.y < this.y + this.height && c.y + c.h > this.y){
            onInteraction(c);                
        }

    }

    abstract void onInteraction(Character c);
}