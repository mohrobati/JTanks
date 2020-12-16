import java.awt.*;
import java.awt.geom.AffineTransform;

public class MachineGunEnemy extends Enemy {

    /**
     * a type of dynamic enemy
     * works exactly like TankEnemy
     * only shots in machineGun form (refer to TankEnemy)
     */

    private Tank playerTank1;
    private Tank playerTank;

    int counter = 0;

    public MachineGunEnemy(int x, int y, ID id, Handler handler, Tank playerTank1, boolean bonus) {
        super(x, y, id, handler,bonus);
        this.playerTank1 = playerTank1;
        this.playerTank = playerTank1;
        hp = 100;
    }

    @Override
    public void tick() {

        playerTank = playerTank1;
        Tank playerTank2 = handler.getClientTank();

        if(handler.tank1IsAlive && getRange().intersects(playerTank1.getBounds())) {
            playerTank = playerTank1;
            rangeIntersection = true;
            if (getBigBounds().intersects(playerTank1.getBounds())) {
                aiIntersection = true;
                velX = 0;
                velY = 0;
                if (counter == 1 || counter == 5 || counter == 9 || counter == 13) {
                    handler.addObject(new Bullet(x + 60, y + 60, ID.Bullet_Machine_Gun, handler, playerTank1.x+50, playerTank1.y+50, this));
                }
                if (counter == 60) counter = 0;
                counter++;
            } else {
                aiIntersection = false;
            }
        }
        else if (handler.tank2IsAlive && getRange().intersects(playerTank2.getBounds())) {
            playerTank = playerTank2;
            rangeIntersection = true;
            if (getBigBounds().intersects(playerTank2.getBounds())) {
                aiIntersection = true;
                velX = 0;
                velY = 0;
                if (counter == 1 || counter == 5 || counter == 9 || counter == 13) {
                    handler.addObject(new Bullet(x + 60, y + 60, ID.Bullet_Machine_Gun, handler, playerTank2.x+51, playerTank2.y+51, this));
                }
                if (counter == 60) counter = 0;
                counter++;
            } else {
                aiIntersection = false;
            }
        }
        else {
            rangeIntersection = false;
        }

        AI(playerTank, 4);

        damage(100,35);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getMachineGunEnemy(),x,y,null);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(x+60 ,y+60);
        affineTransform.rotate(Math.atan2((playerTank.y+50-12)-(y+60), playerTank.x+50-(x+60)));
        affineTransform.translate(-32,-29);
        ((Graphics2D)g).drawImage(Images.getGun3(),affineTransform,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,120,120);
    }

    @Override
    public Rectangle getUpSide() {
        return new Rectangle(x, y, 120, 1);
    }

    @Override
    public Rectangle getDownSide() {
        return new Rectangle(x, y + 120 - 1, 120, 1);
    }

    @Override
    public Rectangle getLeftSide() {
        return new Rectangle(x, y, 1, 120);
    }

    @Override
    public Rectangle getRightSide() {
        return new Rectangle(x + 120 - 1, y, 1, 120);
    }

    public Rectangle getBigBounds() {
        return new Rectangle(x-200,y-200,400,400);
    }
}
