import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A hard wall that anything can't go through it.
 */
public class Block extends GameObject {

    public Block(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getHardWall(), x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 100, 100);
    }
}
