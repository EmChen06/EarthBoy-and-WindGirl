import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends Rectangle{
    BufferedImage image;
    double xx, yy, vx, vy;

    Platform(int x, int y, int width, int height){ //TODO: add BufferedImage img into constructor
        super(x,y,width,height);
        this.xx = this.x;
        this.yy = this.y;
        // this.image = img;
    }

    void move() {
        this.xx += vx;
        this.yy += vy;
        this.x = (int)this.xx;
        this.y = (int)this.yy;
    }

}
