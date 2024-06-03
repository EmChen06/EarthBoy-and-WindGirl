import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.*;

public class EarthBoyWindGirl extends JFrame {

    DrawingPanel draw;
    JPanel p;
    int W = 1000;
    int H = 600;

    WindGirl windGirl;
    EarthBoy earthBoy;

    ArrayList<Platform> platforms = new ArrayList<>();
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
        p.setPreferredSize(new Dimension(W, H));

        draw = new DrawingPanel();

        plat = new Platform(0, 0, 100, 30);
        platforms.add(plat);

        /* 
        for (int i = 0; i < (W / 20); i++) {
            for(int j = 0; j < (H / 20); j++){
                Platform pl = new Platform(i * 20, j*20, 20, 20);
                platforms.add(pl);
            }
        }
        */


        windGirl = new WindGirl(30, H - 40, null, false);
        earthBoy = new EarthBoy(70, H - 40, null, false, false);


        
        draw.setPreferredSize(new Dimension(W, H));

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

            for (Platform platform : platforms) {
                 g2.fillRect(platform.x, platform.y, platform.width, platform.height);
                 }
            
            g2.setColor(Color.blue);
            g2.fillRect(windGirl.x, windGirl.y, windGirl.w, windGirl.h);

            g2.setColor(Color.red);
            g2.fillRect(earthBoy.x, earthBoy.y, earthBoy.w, earthBoy.h);

        }
    }
}