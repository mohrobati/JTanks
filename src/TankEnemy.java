import java.awt.*;
import java.awt.geom.AffineTransform;

public class TankEnemy extends Enemy {

    /**
     * A type of dynamic enemy, which will follow you
     * if you trespassed its range and will shot you
     * afterwards... just like a normal tank
     */

    private Tank playerTank1;
    private Tank playerTank;

    private int counter = 0;

    public TankEnemy(int x, int y, ID id, Handler handler, Tank playerTank1, boolean bonus) {
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
            if(getBigBounds().intersects(playerTank1.getBounds())){
                aiIntersection = true;
                velX=0;
                velY=0;
                if(counter%60==0) {
                    handler.addObject( new Bullet(x + 60, y + 60, ID.Bullet_Cannon, handler, playerTank1.x+50, playerTank1.y+50, this));
                    if(counter==100) counter=0;
                }
                counter++;
            } else {
                aiIntersection = false;
            }
        }
        else if (handler.tank2IsAlive && getRange().intersects(playerTank2.getBounds())) {
            playerTank = playerTank2;
            rangeIntersection = true;
            if(getBigBounds().intersects(playerTank2.getBounds())){
                aiIntersection = true;
                velX=0;
                velY=0;
                if(counter%60==0) {
                    handler.addObject( new Bullet(x + 60, y + 60, ID.Bullet_Cannon, handler, playerTank2.x+49, playerTank2.y+49, this));
                    if(counter==100) counter=0;
                }
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
        g.drawImage(Images.getTankEnemy(),x-10,y-10,null);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(x+50 ,y+50);
        affineTransform.rotate(Math.atan2((playerTank.y+50-12)-(y+50), playerTank.x+50-(x+50)));
        affineTransform.translate(-35,-60);
        ((Graphics2D)g).drawImage(Images.getGun4(),affineTransform,null);
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
        return new Rectangle(x, y + 120 -  1, 120, 1);
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
