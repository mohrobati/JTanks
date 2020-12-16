import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * An enemy which runs to your tank.
 */
public class BugEnemy extends Enemy {

    private Tank playerTank1;
    private Tank playerTank;

    public BugEnemy(int x, int y, ID id, Handler handler, Tank playerTank1, boolean bonus) {
        super(x, y, id, handler,bonus);
        this.playerTank1 = playerTank1;
        this.playerTank = playerTank1;

        hp = 100;
    }

    @Override
    public void tick() {

        playerTank = playerTank1;
        Tank playerTank2 = handler.getClientTank();

        if(handler.tank1IsAlive && getRange().intersects(playerTank1.getBounds())){
            playerTank = playerTank1;
            rangeIntersection = true;
        } else if (handler.tank2IsAlive && getRange().intersects(playerTank2.getBounds())) {
            playerTank = playerTank2;
            rangeIntersection = true;
        }
        else {
            rangeIntersection = false;
        }

        AI(playerTank, 7);

        damage(100,35);

    }

    @Override
    public void render(Graphics g) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(x+50 ,y+50);
        affineTransform.rotate(Math.atan2((playerTank.y+50)-(y+50),(playerTank.x+50)-(x+50)));
        affineTransform.translate(-50,-50);
        ((Graphics2D)g).drawImage(Images.getBugEnemy(),affineTransform,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 100, 100);
    }

    @Override
    public Rectangle getUpSide() {
        return new Rectangle(x, y, 75, 1);
    }

    @Override
    public Rectangle getDownSide() {
        return new Rectangle(x, y + 75 - 1, 75, 1);
    }

    @Override
    public Rectangle getLeftSide() {
        return new Rectangle(x, y, 1, 75);
    }

    @Override
    public Rectangle getRightSide() {
        return new Rectangle(x + 75 - 1, y, 1, 75);
    }
}
