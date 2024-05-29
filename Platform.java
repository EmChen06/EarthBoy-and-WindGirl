import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends Rectangle{
    BufferedImage image;
    Platform(int x, int y, int width, int height, BufferedImage img){
        super(x,y,width,height);
        this.image = img;
    }
}
