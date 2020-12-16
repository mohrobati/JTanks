import java.awt.*;

public class StaticEnemy extends Enemy {

    /**
     * A type of static enemy which
     * will shot you if you trespass its range
     * can be anywhere in the map
     * throws bombs to you...
     */

    private Tank playerTank1;
    private Tank playerTank;
    private int counter=0;

    public StaticEnemy(int x, int y, ID id, Handler handler, Tank playerTank1, boolean bonus) {
        super(x, y, id, handler,bonus);
        this.playerTank1 = playerTank1;
        this.playerTank = playerTank1;
        hp = 300;
    }

    @Override
    public void tick() {

        Tank playerTank2 = handler.getClientTank();

        if(handler.tank1IsAlive && getBigBounds().intersects(playerTank1.getBounds())){
            playerTank = playerTank1;
            if(counter%50==0) {
                Bullet bullet = new Bullet(x + 60, y + 60, ID.Bullet_Bomb, handler, playerTank1.x+50, playerTank1.y+50, this);
                handler.addObject(bullet);
                if(counter==100) counter=0;
            }
            counter++;
        }
        else if (handler.tank2IsAlive && getBigBounds().intersects(playerTank2.getBounds())) {
            playerTank = playerTank2;
            if(counter%50==0) {
                Bullet bullet = new Bullet(x + 60, y + 60, ID.Bullet_Bomb, handler, playerTank2.x+51, playerTank2.y+51, this);
                handler.addObject(bullet);
                if(counter==100) counter=0;
            }
            counter++;
        }

        damage(100,50);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getStaticEnemy(),x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,120,120);
    }

    public Rectangle getBigBounds() {
        return new Rectangle(x-400,y-400,800, 800);
    }
}
