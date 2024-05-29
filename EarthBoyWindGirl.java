import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.*;

public class EarthBoyWindGirl extends JFrame {

    DrawingPanel p;
    int WIDTH = 1000;
    int HEIGHT = 600;
    ArrayList<Platform> platforms = new ArrayList<>();
    
    
    EarthBoyWindGirl(){
        this.setSize(new Dimension(WIDTH,HEIGHT));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        p = new DrawingPanel();
        this.add(p);

        
        platforms.add(new Platform(50, 50, 100, 20, null));

        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    private class DrawingPanel extends JPanel{

        DrawingPanel(){
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;
			//turn on antialiasing
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);


            for (Platform platform : platforms) {
                g2.fillRect(platform.x, platform.y, platform.width, platform.height);
            }
            
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EarthBoyWindGirl();
            }
        });
    }    
}