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
    Timer keyDelay;

    ArrayList<Integer> storedKeys = new ArrayList<>();
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
        windGirl = new WindGirl(500, H - 40, null, false);
        plat = new Platform(W - 300, H - 30, 100, 30);

        // for (int i = 0; i < (WIDTH / 20); i++) {
        //     for(int j = 0; j < (HEIGHT / 20); j++){
        //         Platform p = new Platform(i * 20, j*20, 20, 20, null);
        //         platforms.add(p);
        //     }
        // }

        p.add(draw);
        p.addKeyListener(new kListener());
        keyDelay = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Integer i: storedKeys) {
                
                    if (i == KeyEvent.VK_W) { // up,jump
                        earthBoy.setJump(true);
                        System.out.println("true");
                    } else if (i == KeyEvent.VK_D) { // forward
                        earthBoy.setVX(3.5);
                        earthBoy.move();
                    } else if (i == KeyEvent.VK_A) { // back
                        earthBoy.setVX(-3.5);
                        earthBoy.move();
                    } else if (i == KeyEvent.VK_E) {
                        earthBoy.enterBuildMode();
                    } else if (i == KeyEvent.VK_UP) {
                        windGirl.setJump(true);
                        windGirl.move();
                    } else if (i == KeyEvent.VK_RIGHT) {
                        windGirl.setVX(3.5);
                        windGirl.move();
                    } else if (i == KeyEvent.VK_LEFT) {
                        windGirl.setVX(-3.5);
                        windGirl.move();
                    }
                    draw.repaint();
                }
            }
        });
        keyDelay.start();

        p.setFocusable(true);
        this.setContentPane(p);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private class kListener extends KeyAdapter{
        public void keyPressed(KeyEvent event) {
            Integer e = event.getKeyCode();

            if (!storedKeys.contains(e)) {
                storedKeys.add(e); 
            }
        }

        public void keyReleased(KeyEvent event) {
            Integer e = event.getKeyCode();
            storedKeys.remove(e);
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

            g2.setColor(Color.BLUE);
            g2.fillRect(earthBoy.x, earthBoy.y, 20, 40);
            
            g2.setColor(Color.RED);
            g2.fillRect(windGirl.x, windGirl.y, 20, 40);

            // g2.fillRect(w - 500, h - 100,200,100);

            // for (Platform platform : platforms) {
            //     g2.drawRect(platform.x, platform.y, platform.width, platform.height);
            //     }
            // }
            
        }
    }
}