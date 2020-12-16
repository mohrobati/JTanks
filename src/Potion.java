import java.awt.*;

public class Potion extends GameObject{

    /**
     * a type of crate which will protect you
     * from the bullets for 10 seconds...
     */

    public Potion(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getPOTION(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 16,y,84,100);
    }

}
