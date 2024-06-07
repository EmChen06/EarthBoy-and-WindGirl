import java.awt.image.BufferedImage;

public class QuickSand extends Interactable{

    QuickSand(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h, image);
    }

    @Override
    void onInteraction(Character c) {
        if (c.name.equals("windGirl")){
            c.isDead = true;
            System.out.println("DIE DIE");
        }        
    }

    @Override
    void onLeave(Character c) {        
    }

}