import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class Main {
    public static void main(String[] args) {
        BufferedImage image = null;
        String path = "aim.png";
        File imagefile = new File("../res/"+path);
        try {
            image = ImageIO.read(imagefile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
