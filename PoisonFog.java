import java.awt.image.BufferedImage;

public class PoisonFog extends Interactable{

    PoisonFog(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h, image);
    }

    @Override
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
