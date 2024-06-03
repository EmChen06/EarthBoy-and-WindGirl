import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.*;

public class EarthBoyWindGirl extends JFrame{

    DrawingPanel draw;
    JPanel p;
    int W = 1000;
    int H = 600;

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
    
    EarthBoyWindGirl() {
        this.setTitle("EarthBoy and WindGirl");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        p = new JPanel();
        p.setPreferredSize(new Dimension(W, H));

        draw = new DrawingPanel();

        earthBoy = new EarthBoy(0, H - 40, null, false, false);
        plat = new Platform(W - 300, H - 30, 100, 30);

        // for (int i = 0; i < (WIDTH / 20); i++) {
        //     for(int j = 0; j < (HEIGHT / 20); j++){
        //         Platform p = new Platform(i * 20, j*20, 20, 20, null);
        //         platforms.add(p);
        //     }
        // }

        p.add(draw);
        p.addKeyListener(new kListener());
        p.setFocusable(true);
        this.setContentPane(p);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public class kListener implements KeyListener{
    public void keyPressed(KeyEvent event) {
        int e = event.getKeyCode();
        System.out.println(e);
        if (e == KeyEvent.VK_W) { // up,jump
            earthBoy.setJump(true);
            System.out.println("true");
            draw.repaint();
        } else if (e == KeyEvent.VK_D) { // forward
            earthBoy.setVX(2.5);
            earthBoy.move();
            draw.repaint();
        } else if (e == KeyEvent.VK_A) { // back
            earthBoy.setVX(-2.5);
            draw.repaint();
        } else if (e == KeyEvent.VK_UP) {
            windGirl.setJump(true);
            draw.repaint();
        } else if (e == KeyEvent.VK_RIGHT) {
            windGirl.setVX(2.5);
            draw.repaint();
        } else if (e == KeyEvent.VK_LEFT) {
            windGirl.setVX(-2.5);
            draw.repaint();
        }
    }

    @Override public void keyTyped(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {
        
    }
}

    class DrawingPanel extends JPanel{

        DrawingPanel(){
            this.setPreferredSize(new Dimension(W, H));
        }

        @Override
        public void paintComponent(Graphics g) { //changed to public instead of protected
            super.paintComponent(g); 

            Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //turn on antialiasing

            g2.setColor(Color.black);
            g2.fillRect(plat.x, plat.y, plat.width, plat.height);
            g2.fillRect(earthBoy.x, earthBoy.y, 20, 40);

            // g2.fillRect(w - 500, h - 100,200,100);

            // for (Platform platform : platforms) {
            //     g2.drawRect(platform.x, platform.y, platform.width, platform.height);
            //     }
            // }
            
        }
    }
}