import java.awt.*;

public class UpgradeStar extends GameObject {

    /**
     * A type of crate which will upgrade your current using gun!
     * works on both cannon and machineGun, only one update
     * is available
     */

    public UpgradeStar(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getUpgradeStar(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,50,50);
    }
}
