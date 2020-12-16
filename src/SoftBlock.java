import java.awt.*;

public class SoftBlock extends Block {

    /**
     * a type of block which will be eliminated
     * when it's been shot 4 times, then objects can go
     * through it
     */

    private int bulletCounter = 0;
    private boolean bonus;

    public SoftBlock(int x, int y, ID id, Handler handler, boolean bonus) {
        super(x, y, id, handler);
        this.bonus = bonus;
    }

    public void damage() {
        bulletCounter++;
        if (bulletCounter == 4) {
            if(bonus) handler.addObject(Enemy.getBonus(x, y, handler));
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        if (bulletCounter == 0)
            g.drawImage(Images.getSoftWall1(), x, y, null);
        if (bulletCounter == 1)
            g.drawImage(Images.getSoftWall2(), x, y, null);
        if (bulletCounter == 2)
            g.drawImage(Images.getSoftWall3(), x, y, null);
        if (bulletCounter == 3)
            g.drawImage(Images.getSoftWall4(), x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,96,96);
    }
}
