import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.*;

public class EarthBoyWindGirl extends JFrame {

    DrawingPanel draw;
    JPanel p;
    int WIDTH = 1000;
    int HEIGHT = 600;

    WindGirl windGirl;
    EarthBoy earthBoy;

    // ArrayList<Platform> platforms = new ArrayList<>();
    Platform plat;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EarthBoyWindGirl();
            }
        });
    } 
    
    EarthBoyWindGirl(){
        this.setTitle("EarthBoy and WindGirl");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        p = new JPanel();
        p.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        draw = new DrawingPanel();

        // plat = new Platform(0, 0, 100, 30);

        // for (int i = 0; i < (WIDTH / 20); i++) {
        //     for(int j = 0; j < (HEIGHT / 20); j++){
        //         Platform p = new Platform(i * 20, j*20, 20, 20, null);
        //         platforms.add(p);
        //     }
        // }

        draw.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        p.add(draw);
        this.setContentPane(p);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    class DrawingPanel extends JPanel{

        DrawingPanel(){
        }

        @Override
        public void paintComponent(Graphics g) { //changed to public instead of protected
            super.paintComponent(g); 

            Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //turn on antialiasing

            g2.setColor(Color.black);
            // g2.fillRect(plat.x, plat.y, plat.width, plat.height);

            g2.fillRect(0,0,100,100);

            // for (Platform platform : platforms) {
            //     g2.drawRect(platform.x, platform.y, platform.width, platform.height);
            //     }
            // }
            
        }
    }
}