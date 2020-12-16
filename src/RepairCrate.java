import java.awt.*;

public class RepairCrate extends GameObject {

    /**
     * a type of crate which will fill up
     * the tank's health...
     */

    public RepairCrate(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getRepairCrate(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,70,70);
    }
}
