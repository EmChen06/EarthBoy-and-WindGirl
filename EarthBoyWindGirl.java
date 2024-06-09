
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

public class EarthBoyWindGirl extends JFrame {

    DrawingPanel draw;
    JPanel introP, p;
    JOptionPane quit;
    int W = 1000;
    int H = 600;
    WindGirl windGirl;
    EarthBoy earthBoy;
    Door dEarth, dWind;
    Timer keyDelay, platTimer;
    BufferedImage SS, platformImg, backgroundImg;
    Boolean platPlaced = false, tempPlaced = false, endGame = false;

    static ArrayList<Platform> platforms = new ArrayList<>();
    Platform earthPlat = new Platform(0, 0, 0, 0);
    ArrayList<Integer> storedKeys = new ArrayList<>();

    static ArrayList<Platform> poisonList = new ArrayList<>();
    static ArrayList<Platform> quickSandList = new ArrayList<>();

    PoisonFog poisonFog;
    QuickSand quickSand;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // new EarthBoyWindGirl();
                new Introduction();
            }
        });
    }

    static class Introduction extends JFrame implements ActionListener {

        BufferedImage cover;
        JPanel intro;
        DrawingPanel introDraw;
        JButton start;
        int Width = 1000, Height = 600, transparency = 255;
        Timer fadeIN, fadeOUT;
        Boolean fadeDone = false;

        Introduction() {
            this.setTitle("EarthBoy and WindGirl Introduction");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);

            cover = loadImage("\\Images\\PICTURE.png");

            intro = new JPanel();
            introDraw = new DrawingPanel();
            intro.setPreferredSize(new Dimension(Width, Height));

            fadeIN = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (transparency == 1) {
                        fadeOUT = new Timer(1, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (transparency == 255) {
                                    fadeDone = true;
                                    fadeOUT.stop();
                                    fadeIN.stop();
                                } else {
                                    transparency+=2;
                                    introDraw.repaint();
                                }
                            }
                        });
                        fadeOUT.start();
                    } else {
                        transparency-=2;
                        introDraw.repaint();
                    }
                }
            });
            fadeIN.start();
            
            intro.add(introDraw);
            this.add(intro);
            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }

        class DrawingPanel extends JPanel {
            DrawingPanel() {
                this.setPreferredSize(new Dimension(Width, Height));
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (!fadeDone) {
                    g2.drawImage(cover, 0,0, Width, Height, null);
                    g2.setColor(new Color(0,0,0,transparency));
                    g2.fillRect(0,0,Width,Height);
                } else {
                    g2.setColor(new Color(255, 255, 255));
                    g2.fillRect(0,0,Width, Height);
                }

            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        BufferedImage loadImage(String filename) {
            BufferedImage image = null;
            java.net.URL imageURL = this.getClass().getResource(filename);
            if (imageURL != null) {
                try {
                    image = ImageIO.read(imageURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "An image failed to load: " + filename, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return image;
        }
    

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
        platforms.add(new Platform(-30, H, W + 60, 30));
        platforms.add(new Platform(-30, 0, 30, H + 30));
        platforms.add(new Platform(W, 0, 30, H + 30));

        /* 
        for (int i = 0; i < (W / 20); i++) {
            for(int j = 0; j < (H / 20); j++){
                Platform pl = new Platform(i * 20, j*20, 20, 20);
                platforms.add(pl);
            }
        }
         */
        windGirl = new WindGirl(30, H - 40 - 30, null, false, false);
        earthBoy = new EarthBoy(70, H - 40 - 30, null, false, false, false);
        dWind = new Door(W - 300, H - 300, 40, 100, null);
        dEarth = new Door(W - 450, H - 180, 40, 100, null);

        poisonFog = new PoisonFog(400, 500, 100, 20, null);
        quickSand = new QuickSand(700, 500, 100, 20, null);

        poisonList.add(poisonFog);
        quickSandList.add(quickSand);

        //load images in
        SS = loadImage("\\Images\\EdittedSpriteSheet.png");
        platformImg = loadImage("\\Images\\Platform.png");
        backgroundImg = loadImage("\\Images\\background.png");

        draw.setPreferredSize(new Dimension(W, H));

        p.add(draw);
        p.addKeyListener(new kListener());
        keyDelay = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int eVX = 0;
                int wVX = 0;
                for (Integer i : storedKeys) {
                    if (i == KeyEvent.VK_W) { // up,jump
                        if (!earthBoy.isBuild) {
                            if (!earthBoy.isJump && earthBoy.preparedJump) {
                                earthBoy.setJump(true);
                                earthBoy.setVY(15);
                                earthBoy.setPreparedJump(false);
                            }
                        }
                    } else if (i == KeyEvent.VK_D) { // forward
                        if (!earthBoy.isBuild) {
                            eVX += 3.5;
                        }
                    } else if (i == KeyEvent.VK_A) { // back
                        if (!earthBoy.isBuild) {
                            eVX -= 3.5;
                        }
                    } else if (i == KeyEvent.VK_E && earthBoy.readyBuild && !earthBoy.isJump && !tempPlaced) { //if he presses E again, he can exit
                        if (earthBoy.isBuild) {
                            earthBoy.leaveBuildMode();
                            earthBoy.setReadyBuild(false);
                            if (platforms.contains(earthPlat)) {
                                platforms.remove(earthPlat);
                            }
                            earthPlat = new Platform(0, 0, 0, 0);
                        } else {
                            earthBoy.enterBuildMode();
                            earthPlat = new Platform(20, 20, 100, 30);
                            earthBoy.setReadyBuild(false);
                        }
                    } else if (i == KeyEvent.VK_UP) {
                        if (!windGirl.isJump) {
                            windGirl.setDoubleJump(false);
                        }
                        if (!windGirl.isJump && windGirl.preparedJump) {
                            windGirl.setJump(true);
                            windGirl.setVY(15);
                            windGirl.setPreparedJump(false);
                        }
                        if (windGirl.isJump && !windGirl.isDoubleJump && windGirl.preparedJump) {
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

                poisonFog.checkCollision(earthBoy);
                poisonFog.checkCollision(windGirl);
                quickSand.checkCollision(earthBoy);
                quickSand.checkCollision(windGirl);

                dEarth.checkCollision(earthBoy);
                // System.out.println("Earth: " + dEarth.charDone);
                dWind.checkCollision(windGirl);
                // System.out.println("Wind: " + dWind.charDone);


                if (earthBoy.isBuild) {
                    earthAbility();
                }

                if (platPlaced) {
                    platformDecay();
                    platPlaced = false;
                }

                checkDoor();

                checkDeath();

                checkEnd();

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

    protected void checkDeath() {
        if (earthBoy.isDead || windGirl.isDead){
            JOptionPane.showMessageDialog(null, "U DIED UNLUCKY", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            keyDelay.stop();
            this.dispose();
        }
    }

    protected void checkDoor() {
        if (dEarth.charDone && dWind.charDone) {
            endGame = true;
        }
    }

    protected void checkEnd() {
        if (endGame) {
            System.out.println("quit");
            JOptionPane.showMessageDialog(null, "YOU WON!", "Great Job!", JOptionPane.INFORMATION_MESSAGE);
            keyDelay.stop();
            this.dispose();
        }
    }

    public void earthAbility() {
        earthPlat.setVX(0);
        earthPlat.setVY(0);
        for (Integer i : storedKeys) {
            if (i == KeyEvent.VK_W) {
                earthPlat.setVY(4.0);
            } else if (i == KeyEvent.VK_S) {
                earthPlat.setVY(-4.0);
            } else if (i == KeyEvent.VK_A) {
                earthPlat.setVX(-4.0);
            } else if (i == KeyEvent.VK_D) {
                earthPlat.setVX(4.0);
            } else if (i == KeyEvent.VK_SPACE) {
                platforms.add(earthPlat);
                platPlaced = true;
                tempPlaced = true;
                earthBoy.leaveBuildMode();
            }
        }
        earthPlat.move();
    }

    public void platformDecay() {
        platTimer = new Timer(3500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                platforms.remove(earthPlat);
                earthPlat = new Platform(0, 0, 0, 0);
                draw.repaint();
                tempPlaced = false;
            }
        });
        platTimer.setRepeats(false);
        platTimer.start();
    }

    private class kListener extends KeyAdapter {

        public void keyPressed(KeyEvent event) {
            Integer e = event.getKeyCode();
            if (!storedKeys.contains(e)) {
                storedKeys.add(e);
            }
        }

        public void keyReleased(KeyEvent event) {
            Integer e = event.getKeyCode();
            storedKeys.remove(e);

            if (e == KeyEvent.VK_W) {
                earthBoy.setPreparedJump(true);
            } else if (e == KeyEvent.VK_UP) {
                windGirl.setPreparedJump(true);
            } else if (e == KeyEvent.VK_E) {
                earthBoy.setReadyBuild(true);
            }
        }
    }

    class DrawingPanel extends JPanel {

        DrawingPanel() {
            this.setPreferredSize(new Dimension(W, H));
        }

        @Override
        public void paintComponent(Graphics g) { //changed to public instead of protected
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //turn on antialiasing
            //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); //turn on antialiasing

            //draw background
            g2.drawImage(backgroundImg, 0, 0, W, H, null);

            g2.setColor(Color.black);
        
            for (int i = 0; i < W / 20; i++) {
                for (int j = 0; j < H / 20; j++) {
                    g2.drawRect(i * 20, j * 20, 20, 20);
                }
            }

            for (Platform platform : platforms) {
                //g2.fillRect(platform.x, platform.y, platform.width, platform.height);
                g2.drawImage(platformImg, platform.x, platform.y, platform.width, platform.height, null);
            }

            g2.setColor(new Color(128, 156, 217));
            // g2.fillRect(earthPlat.x, earthPlat.y, earthPlat.width, earthPlat.height);
            g2.drawImage(platformImg, earthPlat.x, earthPlat.y, earthPlat.width, earthPlat.height, null);

            g2.setColor(Color.blue);
            // g2.drawImage(SS, windGirl.x, windGirl.y, windGirl.w, windGirl.h, 200, 200, 100, 100, null);
            //g2.fillRect(windGirl.x, windGirl.y, windGirl.w, windGirl.h);
            g2.fillRect(dWind.x, dWind.y, dWind.width, dWind.height);
            g2.fillRect(poisonFog.x, poisonFog.y, poisonFog.width, poisonFog.height);
            g2.drawImage(SS, windGirl.x, windGirl.y + 15, windGirl.x + (windGirl.w + 15), windGirl.y + (windGirl.h + 50), 938, 300, 1000, 400, null);
            g2.drawImage(SS, windGirl.x, windGirl.y - 10, windGirl.x + windGirl.w, windGirl.y + 20, 37, 560, 92, 633, null);
            
            g2.setColor(Color.red);
            // g2.fillRect(earthBoy.x, earthBoy.y, earthBoy.w, earthBoy.h);
            g2.fillRect(dEarth.x, dEarth.y, dEarth.width, dEarth.height);
            g2.fillRect(quickSand.x, quickSand.y, quickSand.width, quickSand.height);
            g2.drawImage(SS, earthBoy.x, earthBoy.y, earthBoy.x + earthBoy.w + 5, earthBoy.y + earthBoy.h, 180, 417, 230, 480, null); //body
            g2.drawImage(SS, earthBoy.x, earthBoy.y - 5, earthBoy.x + earthBoy.w, earthBoy.y + 23, 37, 67, 92, 126, null); //head

        }
    }

    /**
     * Loads an image from a file in the resource folder
     *
     * @param filename	The name of the file
     * @return	Returns a BufferedImage connected to filename
     */
    BufferedImage loadImage(String filename) {
        BufferedImage image = null;
        java.net.URL imageURL = this.getClass().getResource(filename);
        if (imageURL != null) {
            try {
                image = ImageIO.read(imageURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "An image failed to load: " + filename, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

}
