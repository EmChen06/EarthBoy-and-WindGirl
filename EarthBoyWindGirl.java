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

    static ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Integer> storedKeys = new ArrayList<>();

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
        this.setResizable(false);
        
        p = new JPanel();
        p.setPreferredSize(new Dimension(W, H));

        draw = new DrawingPanel();


        // adding test platforms
        platforms.add(new Platform(W - 300, H - 200, 100, 30));
        platforms.add(new Platform(W - 450, H - 80, 100, 30));
        platforms.add(new Platform(-30, H-30, W+60, 30));
        platforms.add(new Platform(-30, 0, 30, H+30));
        platforms.add(new Platform(W, 0, 30, H+30));

        /* 
        for (int i = 0; i < (W / 20); i++) {
            for(int j = 0; j < (H / 20); j++){
                Platform pl = new Platform(i * 20, j*20, 20, 20);
                platforms.add(pl);
            }
        }
        */


        windGirl = new WindGirl(30, H - 40 - 30, null, false);
        earthBoy = new EarthBoy(70, H - 40 - 30, null, false, false);


        
        draw.setPreferredSize(new Dimension(W, H));

        p.add(draw);
        p.addKeyListener(new kListener());
        keyDelay = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int eVX = 0;
                int wVX = 0;
                for (Integer i: storedKeys) {
                
                    if (i == KeyEvent.VK_W) { // up,jump
                        if (!earthBoy.isJump && earthBoy.preparedJump){
                            earthBoy.setJump(true);
                            earthBoy.setVY(15);
                            earthBoy.setPreparedJump(false);
                        }
                    } else if (i == KeyEvent.VK_D) { // forward
                        eVX += 3.5;
                    } else if (i == KeyEvent.VK_A) { // back
                        eVX -= 3.5;
                    } else if (i == KeyEvent.VK_E) {
                        earthBoy.enterBuildMode();
                    } else if (i == KeyEvent.VK_UP) {
                        System.out.println(windGirl.isDoubleJump);
                        if (!windGirl.isJump){
                            windGirl.setDoubleJump(false);
                        }
                        if (!windGirl.isJump && windGirl.preparedJump){
                            windGirl.setJump(true);
                            windGirl.setVY(15);
                            windGirl.setPreparedJump(false);
                        }
                        if (windGirl.isJump && !windGirl.isDoubleJump && windGirl.preparedJump){
                            windGirl.setDoubleJump(true);
                            windGirl.setVY(15);
                            System.out.println("Double Jump");
                            windGirl.setPreparedJump(false);
                        }
                    } else if (i == KeyEvent.VK_RIGHT) {
                        wVX += 3.5;
                    } else if (i == KeyEvent.VK_LEFT) {
                        wVX -= 3.5;
                    }
                }
                windGirl.setVX(wVX);
                windGirl.move();
                earthBoy.setVX(eVX);
                earthBoy.move();

                draw.repaint();
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

            if (e == KeyEvent.VK_W){
                earthBoy.setPreparedJump(true);
            }
            if (e == KeyEvent.VK_UP){
                windGirl.setPreparedJump(true);
            }
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