import java.awt.*;

public class Teazel extends GameObject {

    /**
     *  A type of block that bullet can go through it
     *  but tanks cannot...
     */

    public Teazel(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getTEAZEL(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,96,96);
    }
}
