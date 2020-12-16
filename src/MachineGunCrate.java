import java.awt.*;

public class MachineGunCrate extends GameObject {

    /**
     * A type of crate which increase the number of
     * machine gun bullets player has...
     */

    public MachineGunCrate(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getMachineGunCrate(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,60,60);
    }
}
