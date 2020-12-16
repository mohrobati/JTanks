import java.awt.*;

public class Plant extends GameObject{

    /**
     * 3 type of plants in the map
     * just for beauty...
     */

    private int type;

    public Plant(int x, int y, ID id, Handler handler, int type) {
        super(x, y, id, handler);
        this.type = type;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        switch (type) {
            case 1: g.drawImage(Images.getPLANT1(),x,y,null); break;
            case 2: g.drawImage(Images.getPLANT2(),x,y,null); break;
            case 3: g.drawImage(Images.getPLANT3(),x,y,null); break;
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
