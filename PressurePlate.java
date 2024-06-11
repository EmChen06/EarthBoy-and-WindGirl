import java.awt.image.BufferedImage;

public class PressurePlate extends Interactable{

    Platform p;

    PressurePlate(int x, int y, int w, int h, BufferedImage image, Platform p) {
        super(x, y, w, h, image);
        this.p = p;
    }

    @Override
    void onInteraction(Character c) {
        //System.out.println("enter");
        p.move();
    }

    @Override
    void onLeave(Character c) {       
        
        p.move(p.vx * -1, p.vy * -1);
    }
}
