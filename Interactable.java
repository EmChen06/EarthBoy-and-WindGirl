import java.awt.image.BufferedImage;

public class Interactable {

    BufferedImage img;
    int x, y, width, height;

    Interactable(int X, int Y, int w, int h, BufferedImage image) {
        this.x = X;
        this.y = Y;
        this.width = w;
        this.height = h;
        this.img = image;
    }

    void onInteraction() {}
}