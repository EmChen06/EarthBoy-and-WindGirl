import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.*;

public class EarthBoyWindGirl extends JFrame {

    JPanel p;
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EarthBoyWindGirl();
            }
        });
    }
}