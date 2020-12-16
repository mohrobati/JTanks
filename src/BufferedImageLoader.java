import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;


/**
 * Loads images and GIFs from resources.
 */
public class BufferedImageLoader {

    private BufferedImage image;
    private Image gif;

    public BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(new File("../res/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public Image loadGif(String path) {
        gif = new ImageIcon("../res/" + path).getImage();
        return gif;
    }
}
