package shell.sijoumi.etatcuve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Cuve {

    private static final String WINDOW_TITLE = "Etat des cuves";
    private static final Color BACKGROUND_COLOR = new Color(135, 206, 235);

    private Cuve() {
        // Prevent instantiation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cuve::createAndShowWindow);
    }

    private static void createAndShowWindow() {
        JFrame frame = new JFrame(WINDOW_TITLE);

        JPanel panel = new GraphiqueCuve();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setForeground(Color.BLACK);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(loadImage("shell.png"));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static Image loadImage(String fileName) {
        URL imageUrl = Cuve.class.getResource("/" + fileName);

        if (imageUrl == null) {
            System.err.println("Image not found: " + fileName);
            return null;
        }

        return new ImageIcon(imageUrl).getImage();
    }
}