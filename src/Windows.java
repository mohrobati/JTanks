import javax.swing.*;
import java.awt.*;

public class Windows extends JFrame {

    /**
     * builds the main Jframe for the game
     * and various canvases will be inserted on it
     * @param width width
     * @param height height
     * @param title title of main frame
     * @author MatinJaffariAghaei 9631903
     * @author MohammadRobatiShirzad 9631028
     */

    public Windows(int width, int height, String title) {

        setPreferredSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        setTitle(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    /**
     * Main method for the game
     * everything starts here...
     */

    public static void main(String[] args) {
        Images.importImages();
        Windows windows = new Windows(1000, 563, "JTanks");
        new MainMenu(windows);
    }
}
