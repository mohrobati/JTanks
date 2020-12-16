import java.awt.*;
import java.util.Random;

/**
 * It has the 4 sides of enemies.
 * which are used to determine the place of collision with enemies.
 * Also this class has an AI method for enemies to follow our tanks.
 * Some enemies will throws bonus when they die.
 */
public class Enemy extends GameObject {

    protected int hp;
    private boolean bonus;

    public Enemy(int x, int y, ID id, Handler handler,boolean bonus) {

        super(x, y, id, handler);
        this.bonus = bonus;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public void AI(Tank playerTank, int vel) {

        if (!aiIntersection && rangeIntersection) {

            velX = (float) ((playerTank.x - x) * vel / Math.sqrt(((playerTank.x - x) * (playerTank.x - x)) + ((playerTank.y - y) * (playerTank.y - y))));
            velY = (float) ((playerTank.y - y) * vel / Math.sqrt(((playerTank.x - x) * (playerTank.x - x)) + ((playerTank.y - y) * (playerTank.y - y))));

            for (int i = 0; i < handler.getObjects().size(); i++) {
                GameObject tempObject = handler.getObjects().get(i);
                if (tempObject.getId() == ID.Block || tempObject.getId() == ID.SoftBlock || tempObject.getId() == ID.Teazel || (tempObject instanceof Enemy && tempObject != this)) {

                    if (getUpSide().intersects(tempObject.getBounds())) {
                        velY = 0;
                        y++;
                    }

                    if (getDownSide().intersects(tempObject.getBounds())) {
                        velY = 0;
                        y--;
                    }

                    if (getRightSide().intersects(tempObject.getBounds())) {
                        velX = 0;
                        x--;
                    }

                    if (getLeftSide().intersects(tempObject.getBounds())) {
                        velX = 0;
                        x++;
                    }
                }
            }

            x += velX;
            y += velY;
        }

    }

    /**
     * Calculates the damage that the enemy must take.
     */
    public void damage(int cannonGunDamage, int machineGunDamage) {
        machineGunDamage = machineGunDamage/3;
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject tempObject = handler.getObjects().get(i);
                if (tempObject.getId() == ID.Bullet_Cannon) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (((Bullet) tempObject).getShooter().getId() != ID.Enemy) {
                            hp = hp - cannonGunDamage/handler.difficulty;
                            handler.addObject(new Explosion(x + getBounds().width / 2, y + getBounds().height / 2, ID.Explosion, handler));
                            handler.removeObject(tempObject);
                        }
                    }
                } else if (tempObject.getId() == ID.Bullet_Machine_Gun) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (((Bullet) tempObject).getShooter().getId() != ID.Enemy) {
                            hp = hp - machineGunDamage/handler.difficulty;
                            handler.removeObject(tempObject);
                        }
                    }
                }
            }
        if(hp<=0){
            if(bonus) handler.addObject(getBonus(x, y, handler));
            handler.removeObject(this);
        }
    }

    /**
     * The Bonus that remains after the enemy when it dies.
     */
    public static GameObject getBonus(int x, int y, Handler handler){
        Random random = new Random();
        int type = random.nextInt(5);
        switch (type){
            case 0: return new CannonCrate(x,y,ID.Crate_Cannon,handler);
            case 1: return new MachineGunCrate(x,y,ID.Crate_Machine_Gun,handler);
            case 2: return new RepairCrate(x,y,ID.Crate_Repair,handler);
            case 3: return new UpgradeStar(x,y,ID.Upgrade_Star,handler);
            case 4: return new Potion(x,y,ID.Potion,handler);
        }
        return null;
    }
}
