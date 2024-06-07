import java.awt.image.BufferedImage;
import java.awt.*;

public class Door extends Interactable {

    boolean charDone = false;

    Door(int x, int y, int w, int h, BufferedImage image) {
        super(x, y, w, h, image);
    }

    public void onInteraction(Character character) {
        this.charDone = true;
    }



}