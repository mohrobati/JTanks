import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class MapSaver implements Runnable {

    /**
     * This class which works in a different thread
     * is made for saving the map that user
     * has been drawn into a png file with the saveToPng method...
     */

    private byte[] aByteArray;

    public MapSaver(byte[] aByteArray) {
        this.aByteArray = aByteArray;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        saveToPng(aByteArray);
    }

    /**
     *
     * @param aByteArray holds the rgb raster of every pixel...
     */

    private void saveToPng(byte[] aByteArray) {
        int width = 20;
        int height = 20;

        DataBuffer buffer = new DataBufferByte(aByteArray, aByteArray.length);

        //3 bytes per pixel: red, green, blue
        WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, 3 * width, 3, new int[]{0, 1, 2}, (Point) null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        BufferedImage image = new BufferedImage(cm, raster, true, null);

        try {
            ImageIO.write(image, "png", new File("res/mapEditor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
