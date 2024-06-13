
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.Timer;

public class EarthBoyWindGirl {

    DrawingPanel draw;
    JPanel introP, p;
    JOptionPane quit;
    int W = 1000;
    int H = 600;
    static WindGirl windGirl;
    static EarthBoy earthBoy;
    Door dEarth, dWind;
    Timer keyDelay, platTimer, animeTimer;
    BufferedImage SS, platformImg, backgroundImg, eDoor, wDoor, PressureP, fart, sand, Msheet;
    Boolean platPlaced = false, tempPlaced = false, endGame = false;
    Boolean eLeft, eRight, eUp, eDown, wLeft, wRight, wUp, wDown; //Booleans for animation
    static JFrame window = new JFrame();
    static JFrame window2 = new JFrame();
    int aCount = 1;

    static ArrayList<Platform> platforms = new ArrayList<>();
    Platform earthPlat = new Platform(0, 0, 0, 0);
    ArrayList<Integer> storedKeys = new ArrayList<>();

    static ArrayList<PoisonFog> poisonList = new ArrayList<>();
    static ArrayList<QuickSand> quickSandList = new ArrayList<>();
    static ArrayList<PressurePlate> pressurePlateList = new ArrayList<>();

    static ArrayList<Platform> interactableList = new ArrayList<>();

    PoisonFog poisonFog;
    QuickSand quickSand;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               new Introduction();
                // new Menu();
                // new EarthBoyWindGirl();
            }
        });
    }

    //Fade In, Fade Out
    static class Introduction {

        BufferedImage cover;
        JPanel intro;
        DrawingPanel introDraw;
        int Width = 1000, Height = 600, transparency = 255;
        Timer fadeIN, fadeOUT;
        Boolean fadeDone = false, fadeInDone = false;

        Introduction() {
            //Setup
            window.setTitle("EarthBoy and WindGirl Introduction");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            intro = new JPanel();
            introDraw = new DrawingPanel();
            intro.setPreferredSize(new Dimension(Width, Height));

            cover = loadImage("\\Images\\PICTURE.png");

            fadeIN = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (transparency == 1) {
                        fadeOUT = new Timer(10, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (transparency == 255 && fadeInDone) {
                                    fadeDone = true;
                                    fadeOUT.stop();
                                    introDraw.repaint();
                                    intro.setVisible(false);
                                } else {
                                    transparency += 2;
                                    introDraw.repaint();
                                }

                            }
                        });
                        fadeInDone = true;
                        fadeOUT.start();
                    } else if (!fadeInDone) {
                        transparency -= 2;
                        introDraw.repaint();
                    }
                    checkFadeDone();
                }
            });
            fadeIN.start();

            introDraw.repaint();
            intro.add(introDraw);
            window.add(intro);
            window.pack();
            window.setVisible(true);
            window.setLocationRelativeTo(null);
        }

        class DrawingPanel extends JPanel {

            DrawingPanel() {
                this.setPreferredSize(new Dimension(Width, Height));
            }

            @Override
            public void paintComponent(Graphics g) {
                //Graphics setup
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.drawImage(cover, 0, 0, Width, Height, null);
                g2.setColor(new Color(0, 0, 0, transparency));
                g2.fillRect(0, 0, Width, Height);

            }
        }

        void checkFadeDone() {
            if (fadeDone) {
                fadeIN.stop();
                window.setVisible(false);
                new Menu();
            }
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

    static class Menu implements ActionListener {

        BufferedImage img;
        JPanel menu;
        DrawingPanel menuDraw;
        JButton start;
        int Width = 800, Height = 200;
        JRadioButton l1, l2, l3;
        ButtonGroup b;
        int map;

        Menu() {
            //Setup
            window2.setTitle("EarthBoy and WindGirl Menu");
            window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window2.setResizable(false);
            menu = new JPanel();
            menu.setPreferredSize(new Dimension(Width, Height));
            menu.setLayout(new BorderLayout(10, 10));

            JPanel flowPanel = new JPanel();
            flowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            menuDraw = new DrawingPanel();

            //Adding map options and start button
            b = new ButtonGroup();

            l1 = new JRadioButton("Map A");
            l1.setActionCommand("L1");
            l1.addActionListener(this);
            b.add(l1);
            flowPanel.add(l1);

            l2 = new JRadioButton("Map B");
            l2.setActionCommand("L2");
            l2.addActionListener(this);
            b.add(l2);
            flowPanel.add(l2);

            l3 = new JRadioButton("Map C");
            l3.setActionCommand("L3");
            l3.addActionListener(this);
            b.add(l3);
            flowPanel.add(l3);

            start = new JButton("Start");
            start.setActionCommand("Start");
            start.addActionListener(this);
            flowPanel.add(start);
            start.setEnabled(false);

            menuDraw.repaint();
            menu.add(menuDraw, BorderLayout.CENTER);
            menu.add(flowPanel, BorderLayout.PAGE_END);
            window2.add(menu);
            window2.pack();
            window2.setVisible(true);
            window2.setLocationRelativeTo(null);
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

                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Calibri", Font.BOLD, 15));
                g2.drawString("EarthBoy Instructions:", 0, 15);
                g2.setFont(new Font("Calibri", Font.PLAIN, 15));
                g2.drawString("[W] for jump, [A] for left, [D] for right", 0, 40);
                g2.drawString("[E] to create platform and use WASD to move around", 0, 65);
                g2.drawString("[Space] to place platform", 0, 90);

                g2.setFont(new Font("Calibri", Font.BOLD, 15));
                g2.drawString("WindGirl Instructions:", 450, 15);
                g2.setFont(new Font("Calibri", Font.PLAIN, 15));
                g2.drawString("[UP] for jump, [LEFT] for left, [RIGHT] for right", 450, 40);
                g2.drawString("Quickly double tap [UP] to double jump", 450, 65);
                g2.drawString("Hold [UP] while jumping and use arrow keys to drift", 450, 90);

            }
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            String e = event.getActionCommand();
            if (e.equals("L1")) {
                map = 1;
                start.setEnabled(true);
            } else if (e.equals("L2")) {
                map = 2;
                start.setEnabled(true);
            } else if (e.equals("L3")) {
                map = 3;
                start.setEnabled(true);
            } else if (e.equals("Start")) {
                window2.setVisible(false);
                new EarthBoyWindGirl(map);
            }
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

    EarthBoyWindGirl(int mapChoice) {

        //Window setup
        window.setTitle("EarthBoy and WindGirl");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        p = new JPanel();
        p.setPreferredSize(new Dimension(W, H));
        draw = new DrawingPanel();

        //All animation variables
        eLeft = false;
        eRight = false;
        eUp = false;
        eDown = false;
        wLeft = false;
        wRight = false;
        wUp = false;
        wDown = false;

        //Floor + Ceiling
        platforms.add(new Platform(-30, H, W + 60, 30));
        platforms.add(new Platform(-30, -30, W + 60, 30));
        //Side Walls
        platforms.add(new Platform(-30, 0, 30, H + 30));
        platforms.add(new Platform(W, 0, 30, H + 30));

        //Choosing maps
        if (mapChoice == 1) {
            level1();
        } else if (mapChoice == 2) {
            level2();
        } else if (mapChoice == 3) {
            level3();
        }

        //load images in
        SS = loadImage("\\Images\\EdittedSpriteSheet.png");
        platformImg = loadImage("\\Images\\Platform.png");
        backgroundImg = loadImage("\\Images\\background.png");
        eDoor = loadImage("\\Images\\EarthBoyDoor.png");
        wDoor = loadImage("\\Images\\WindGirlDoor.png");
        PressureP = loadImage("\\Images\\Plate.png");
        fart = loadImage("\\Images\\fart.png");
        sand = loadImage("\\Images\\sand.png");
        Msheet = loadImage("\\Images\\MirroredSpriteSheet.png");

        // draw.setPreferredSize(new Dimension(W, H));
        p.add(draw);
        p.addKeyListener(new kListener());

        //movement
        keyDelay = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int eVX = 0;
                int wVX = 0;
                for (Integer i : storedKeys) {
                    if (i == KeyEvent.VK_W) { // up,jump
                        if (!earthBoy.isBuild) {
                            if (!earthBoy.isJump && earthBoy.preparedJump) {
                                earthBoy.setJump(true);
                                earthBoy.setVY(14);
                                earthBoy.setPreparedJump(false);
                                eUp = true;
                                eLeft = false;
                                eRight = false;
                            }
                        }
                    } else if (i == KeyEvent.VK_D) { // forward
                        if (!earthBoy.isBuild) {
                            eVX += 3.5;
                            eRight = true;
                            eUp = false;
                            eLeft = false;
                        }
                    } else if (i == KeyEvent.VK_A) { // back
                        if (!earthBoy.isBuild) {
                            eVX -= 3.5;
                            eLeft = true;
                            eUp = false;
                            eRight = false;
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
                            earthPlat = new Platform(earthBoy.x + 10, earthBoy.y + 10, 100, 20);
                            earthBoy.setReadyBuild(false);
                        }
                    } else if (i == KeyEvent.VK_UP) {
                        if (!windGirl.isJump) {
                            windGirl.setDoubleJump(false);
                            wUp = true;
                            wLeft = false;
                            wRight = false;
                        }
                        if (!windGirl.isJump && windGirl.preparedJump) {
                            windGirl.setJump(true);
                            windGirl.setVY(14);
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
                        wRight = true;
                        wUp = false;
                        wLeft = false;
                    } else if (i == KeyEvent.VK_LEFT) {
                        wVX -= 3.5;
                        wLeft = true;
                        wUp = false;
                        wRight = false;
                    }
                }
                windGirl.setVX(wVX);
                windGirl.move();
                earthBoy.setVX(eVX);
                earthBoy.move();

                for (PoisonFog p : poisonList) {
                    p.checkCollision(earthBoy);
                }

                for (QuickSand q : quickSandList) {
                    q.checkCollision(windGirl);
                }

                for (PressurePlate p : pressurePlateList) {
                    p.checkCollision(earthBoy, windGirl);
                }

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

        animeTimer = new Timer(80, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (aCount < 4) {
                    aCount++;
                } else if (aCount >= 4) {
                    aCount = 1;
                }
            }
        });
        animeTimer.start();

        window.setContentPane(p);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        p.setFocusable(true);
        p.requestFocus();

    }

    protected void level1() {
        //Characters
        windGirl = new WindGirl(30, H - 70, null, false, false);
        earthBoy = new EarthBoy(30, H - 150, null, false, false, false);

        //Doors
        dWind = new Door(W - 100, H - 480, 40, 80, null);
        dEarth = new Door(W - 200, H - 480, 40, 80, null);

        //Poison fog + Quicksand
        poisonFog = new PoisonFog(220, H - 280, 500, 20, null);
        quickSand = new QuickSand(300, H - 420, 200, 20, null);
        poisonList.add(poisonFog);
        quickSandList.add(quickSand);

        interactableList.addAll(poisonList);
        interactableList.addAll(quickSandList);
        interactableList.addAll(pressurePlateList);
        interactableList.add(dEarth);
        interactableList.add(dWind);

        //Additional Platforms
        platforms.add(new Platform(0, H - 200, W - 200, 20));
        platforms.add(new Platform(W - 300, H - 50, 100, 20));
        platforms.add(new Platform(200, H - 310, 400, 20));
        platforms.add(new Platform(35, H - 260, 100, 20));

        Platform movingPlatform = new Platform(200, H - 290, 20, 90, 0, 1, 190, H - 350, 230, H - 200);
        platforms.add(movingPlatform);

        platforms.add(new Platform(300, H - 400, W - 300, 20));
        platforms.add(new Platform(300, H - 540, 20, 70));
        platforms.add(new Platform(320, H - 540, 80, 20));

        // Add pressure plate
        PressurePlate plate = new PressurePlate(320, H - 550, 20, 10, null, movingPlatform);
        pressurePlateList.add(plate);

        // Add start platforms
        platforms.add(new Platform(0, H - 100, 100, 20));
    }

    protected void level2(){
        //Characters
        windGirl = new WindGirl(20, 40, null, false, false);
        earthBoy = new EarthBoy(W - 40, 40, null, false, false, false);
        
        //Doors
        dWind = new Door(220, H - 80, 40, 80, null);
        dEarth = new Door(W - 220, H - 80, 40, 80, null);
        
        //Poison fog + Quicksand
        poisonList.add(new PoisonFog(W-280, 120, 180, 20, null));
        poisonList.add(new PoisonFog(W-420, 360, 160, 20, null));
        poisonList.add(new PoisonFog(W-440, H-20, 160, 20, null));
        
        quickSandList.add(new QuickSand(300, H - 20, 140, 20, null));
        quickSandList.add(new QuickSand(200, 420, 80, 20, null));
        quickSandList.add(new QuickSand(240, 280, 140, 20, null));

        
        interactableList.addAll(poisonList);
        interactableList.addAll(quickSandList);
        interactableList.addAll(pressurePlateList);
        interactableList.add(dEarth);
        interactableList.add(dWind);
        
        //Additional Platforms
        platforms.add(new Platform(0, 80, 100, 20));
        platforms.add(new Platform(400, 160, 80, 20));
        platforms.add(new Platform(380, 280, 100, 20));
        platforms.add(new Platform(0, 420, 200, 20));
        platforms.add(new Platform(280, 420, 60, 20));
        platforms.add(new Platform(340, 420, 20, 40));

        platforms.add(new Platform(W-100, 80, 100, 20));
        platforms.add(new Platform(W-100, 100, 20, 40));
        platforms.add(new Platform(W-420, 120, 140, 20));
        platforms.add(new Platform(W-420, 220, 180, 20));
        platforms.add(new Platform(W-220, 220, 120, 20));
        platforms.add(new Platform(W-260, 360, 260, 20));
        platforms.add(new Platform(W-300, 440, 300, 20));
        platforms.add(new Platform(W-300, 460, 20, 40));


        Platform movingPlatform1 = new Platform(390, 80, 20, 100, 0, -1, 390, 100, 410, 280);
        Platform movingPlatform2 = new Platform(W-480, 120, 60, 20, 0, -1, W-480, 120, W-420, 240);
        Platform movingPlatform3 = new Platform(W-240, 140, 20, 100, 0, -1, W-240, 140, W-220, 360);
        platforms.add(movingPlatform1);
        platforms.add(movingPlatform2);
        platforms.add(movingPlatform3);


        platforms.add(new Platform(480, 0, 40, H));
        
        
       // Add pressure plate
       PressurePlate plate1 = new PressurePlate(420, 150, 20, 10, null, movingPlatform2);
       PressurePlate plate2 = new PressurePlate(440, 270, 20, 10, null, movingPlatform3);
       PressurePlate plate3 = new PressurePlate(W-360, 110, 20, 10, null, movingPlatform1);

        pressurePlateList.add(plate1);
        pressurePlateList.add(plate2);
        pressurePlateList.add(plate3);


        
   }

    protected void level3() {
        windGirl = new WindGirl(30, H - 40, null, false, false);
        earthBoy = new EarthBoy(W - 30, H - 150, null, false, false, false);

        dEarth = new Door(W - 40, H - 60, 40, 60, null);
        dWind = new Door(370, 0, 40, 60, null);

        platforms.add(new Platform(150, H - 80, W - 150, 20));
        platforms.add(new Platform(150, 60, 20, 460));
        platforms.add(new Platform(170, 60, 60, 20));
        platforms.add(new Platform(170, 170, 60, 20));
        platforms.add(new Platform (170, 260, 500, 20));
        platforms.add(new Platform(350, 0, 20, 150));
        platforms.add(new Platform(370, 60, 500, 20));
        platforms.add(new Platform(850, 80, 20, H - 230));
        platforms.add(new Platform(500, 180, 100, 20));
        platforms.add(new Platform(W - 250, 300, 100, 20));
        platforms.add(new Platform(230, 380, 520, 20));
        platforms.add(new Platform(80, H - 200, 70, 20));
        platforms.add(new Platform(0, H - 400, 70, 20));
        platforms.add(new Platform(80, H - 500, 70, 20));

        platforms.add(new Platform(W - 130, H - 200, 50, 20));
        platforms.add(new Platform(W - 70, H - 400, 70, 20));
        platforms.add(new Platform(W - 130, H - 500, 70, 20));

        quickSandList.add(new QuickSand(300, 240, 380, 20, null));
        quickSandList.add(new QuickSand(230, 360, 460, 20, null));
        poisonList.add(new PoisonFog(740, 400, 110, 20, null));

        interactableList.addAll(poisonList);
        interactableList.addAll(quickSandList);
        interactableList.addAll(pressurePlateList);
        interactableList.add(dEarth);
        interactableList.add(dWind);

    }

    protected void checkDeath() {
        if (earthBoy.isDead || windGirl.isDead) {
            JOptionPane.showMessageDialog(null, "U DIED UNLUCKY", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            keyDelay.stop();
            animeTimer.stop();
            window.dispose();
            new Menu();
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
            animeTimer.stop();
            window.dispose();
            new Menu();
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
        public void paintComponent(Graphics g) {
            //Drawing setup
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //turn on antialiasing

            //Draw background
            g2.drawImage(backgroundImg, 0, 0, W, H, null);

            //Draw grid lines
            g2.setColor(Color.black);
            for (int i = 0; i < W / 20; i++) {
                for (int j = 0; j < H / 20; j++) {
                    g2.drawRect(i * 20, j * 20, 20, 20);
                }
            }

            //Draws the Doors
            g2.drawImage(wDoor, dWind.x, dWind.y, dWind.width, dWind.height, null);
            g2.drawImage(eDoor, dEarth.x, dEarth.y, dEarth.width, dEarth.height, null);

            //Pressure plates
            for (PressurePlate pressurePlate : pressurePlateList) {
                g2.drawImage(PressureP, pressurePlate.x, pressurePlate.y, pressurePlate.width, pressurePlate.height, null);
            }

            //All platforms
            for (Platform platform : platforms) {
                g2.drawImage(platformImg, platform.x, platform.y, platform.width, platform.height, null);
            }
            //EarthBoy's platform
            g2.drawImage(platformImg, earthPlat.x, earthPlat.y, earthPlat.width, earthPlat.height, null);

            //Poison Fog
            g2.setColor(Color.blue);
            for (PoisonFog poisonFog : poisonList) {
                g2.drawImage(fart, poisonFog.x, poisonFog.y, poisonFog.width, poisonFog.height, null);
            }

            //Quicksand
            g2.setColor(Color.red);
            for (QuickSand quickSand : quickSandList) {
                g2.drawImage(sand, quickSand.x, quickSand.y, quickSand.width, quickSand.height, null);
            }

            //draw windgirl
            if ((!wLeft && !wRight && !wUp) || (windGirl.vx == 0 && windGirl.vy == 0)) { //standing still
                wLeft = false;
                wRight = false;
                wUp = false;
                g2.drawImage(SS, windGirl.x, windGirl.y + 15, windGirl.x + (windGirl.w + 15), windGirl.y + (windGirl.h + 50), 938, 300, 1000, 400, null);
                g2.drawImage(SS, windGirl.x, windGirl.y - 10, windGirl.x + windGirl.w, windGirl.y + 20, 37, 560, 92, 633, null);
            } else if (wLeft) {
                g2.drawImage(Msheet, windGirl.x, windGirl.y - 10, windGirl.x + windGirl.w + 20, windGirl.y + 20, 1290, 242, 1391, 299, null); //head left
                if (aCount == 1 || aCount == 3){
                    g2.drawImage(Msheet, windGirl.x, windGirl.y + 15, windGirl.x + windGirl.w, windGirl.y + windGirl.h, 526, 476, 557, 512, null); //body left 1
                } else if (aCount == 2 || aCount == 4){
                    g2.drawImage(Msheet, windGirl.x, windGirl.y+15, windGirl.x + (windGirl.w), windGirl.y + (windGirl.h), 124, 475, 151, 510, null); //body left 2
                }
            } else if (wRight) {
                g2.drawImage(SS, windGirl.x - 20, windGirl.y - 10, windGirl.x + windGirl.w, windGirl.y + 20, 17, 238, 122, 300, null); //head right
                if (aCount == 1 || aCount == 3){
                    g2.drawImage(SS, windGirl.x-3, windGirl.y+8, windGirl.x + (windGirl.w), windGirl.y + (windGirl.h), 850, 470, 888, 510, null); //body right 1
                } else if (aCount == 2 || aCount == 4){
                    g2.drawImage(SS, windGirl.x-5, windGirl.y+15, windGirl.x + (windGirl.w)-5 , windGirl.y + (windGirl.h), 1258, 475, 1281, 510, null); //body right 2
                }
                

            } else if (wUp) {
                g2.drawImage(SS, windGirl.x, windGirl.y, windGirl.x + windGirl.w, windGirl.y + 30, 301, 570, 362, 660, null); //head up
                g2.drawImage(SS, windGirl.x, windGirl.y + 15, windGirl.x + (windGirl.w + 15), windGirl.y + (windGirl.h + 50), 938, 300, 1000, 400, null); //body default
            }

            //draw Earthboy
            if ((!eLeft && !eRight && !eUp) || (earthBoy.vx == 0 && earthBoy.vy == 0)) { //standing still
                eLeft = false;
                eRight = false;
                eUp = false;
                g2.drawImage(SS, earthBoy.x, earthBoy.y, earthBoy.x + earthBoy.w + 5, earthBoy.y + earthBoy.h, 180, 417, 230, 480, null); //body default
                g2.drawImage(SS, earthBoy.x, earthBoy.y - 5, earthBoy.x + earthBoy.w, earthBoy.y + 23, 37, 67, 92, 126, null); //head default
            } else if (eLeft) {
                g2.drawImage(Msheet, earthBoy.x, earthBoy.y - 20, earthBoy.x + earthBoy.w, earthBoy.y + 23, 1183, 65, 1241, 121, null); //head left
                if (aCount == 1 || aCount == 3) {
                    g2.drawImage(Msheet, earthBoy.x, earthBoy.y, earthBoy.x + earthBoy.w + 5, earthBoy.y + earthBoy.h, 1066, 444, 1094, 480, null); //body left 1
                } else if (aCount == 2 || aCount == 4){
                    g2.drawImage(Msheet, earthBoy.x, earthBoy.y, earthBoy.x + earthBoy.w + 5, earthBoy.y + earthBoy.h, 664, 445, 682, 480, null); //body left 2
                }
            } else if (eRight) {
                g2.drawImage(SS, earthBoy.x + 5, earthBoy.y, earthBoy.x + earthBoy.w, earthBoy.y + 23, 168, 65, 231, 125, null); //head right
                if (aCount == 1 || aCount == 3) {
                    g2.drawImage(SS, earthBoy.x, earthBoy.y, earthBoy.x + earthBoy.w + 5, earthBoy.y + earthBoy.h, 314, 440, 350, 479, null); //body right 1    
                } else if (aCount == 2 || aCount == 4){
                    g2.drawImage(SS, earthBoy.x, earthBoy.y, earthBoy.x + earthBoy.w + 5, earthBoy.y + earthBoy.h, 724, 445, 743, 479, null); //body right 2
                }  
            } else if (eUp) {
                g2.drawImage(SS, earthBoy.x, earthBoy.y - 5, earthBoy.x + earthBoy.w, earthBoy.y + 23, 436, 60, 496, 120, null); //head up
                g2.drawImage(SS, earthBoy.x, earthBoy.y, earthBoy.x + earthBoy.w + 5, earthBoy.y + earthBoy.h, 180, 417, 230, 480, null); //body default 
            }
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
