import java.awt.*;

public class Mine extends Enemy {

    /**
     * A type of static enemy which works like war mine
     * will be exploded if touched
     */

    public Mine(int x, int y, ID id, Handler handler, boolean bonus) {
        super(x, y, id, handler,bonus);
        hp = 100;
    }

    @Override
    public void tick() {
        damage(100,100);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getMINE(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 25,y + 25,45,45);
    }
}
