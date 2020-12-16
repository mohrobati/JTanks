import java.awt.*;

/**
 * If the tank use a cannonCrate,
 * its bullets for cannon will become full.
 */
public class CannonCrate extends GameObject{

    public CannonCrate(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getCannonCrate(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,60,60);
    }

}
