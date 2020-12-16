import java.awt.*;

/**
 * The key that will transfer the tank to the next level.
 */
public class FinalKey extends GameObject{

    public FinalKey(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getKEY(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,100,100);
    }

}
