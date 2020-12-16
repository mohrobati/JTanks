import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A bullet is fired from a tank.
 * It has the coordination of its origin and its destination
 * and is drawn in the line between them by the time.
 */
public class Bullet extends GameObject {


    private final float thetha;

    private GameObject shooter;
    private boolean intersectsWithShooter = true;

    private boolean playBulletCannon;
    private boolean playBulletMachineGun;

    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, GameObject shooter) {
        super(x, y, id, handler);

        this.shooter = shooter;

        mx += 30;
        my += 30;

        thetha = (float) Math.atan2(my-(shooter.y+50),mx-(shooter.x+50));

        velX = (mx - shooter.x-50)/20;
        velY = (my - shooter.y-50)/20;

        velX = (float) (10 * Math.cos(thetha));
        velY = (float) (10 * Math.sin(thetha));

        Sounds sounds = new Sounds();

        if(id == ID.Bullet_Cannon) {
            sounds.playCannonSound();
            if (handler.gameMode == 2)
                playBulletCannon = true;
        }
        if(id == ID.Bullet_Machine_Gun) {
            sounds.playMachineGunSound();
            if (handler.gameMode == 2)
                playBulletMachineGun = true;
        }
    }

    /**
     * Tells the client to play the bullet cannon sound.
     */
    public void setPlayBulletCannon(boolean playBulletCannon) {
        this.playBulletCannon = playBulletCannon;
    }

    /**
     * Tells the client to play the machine gun cannon sound.
     */
    public void setPlayBulletMachineGun(boolean playBulletMachineGun) {
        this.playBulletMachineGun = playBulletMachineGun;
    }

    /**
     * Sets the handler for bullets.
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * Give the shooter of this bullet.
     */
    public GameObject getShooter() {
        return shooter;
    }

    @Override
    public void tick() {

        if(shooter instanceof Tank){
            if(((((Tank) shooter).isUpgradedForCannon())&&(((Tank) shooter).getGunStyle()==1))
                    || ((((Tank) shooter).isUpgradedForMachineGun())&&(((Tank) shooter).getGunStyle()==3))){
                x += 5 * velX;
                y += 5 * velY;
            } else {
                x += 2 * velX;
                y += 2 * velY;
            }
        } else {
            x += 2 * velX;
            y += 2 * velY;
        }


        for (int i = 0; i < handler.getObjects().size(); i++) {

            try {

                GameObject tempObject = handler.getObjects().get(i);

                if (tempObject.getId() == ID.Block)
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (getId() != ID.Bullet_Machine_Gun)
                            handler.addObject(new Explosion(x + getBounds().width / 2, y + getBounds().height / 2, ID.Explosion, handler));
                        handler.removeObject(this);
                        break;
                    }
                if (tempObject.getId() == ID.SoftBlock)
                    if (getBounds().intersects(tempObject.getBounds())) {
                        SoftBlock softBlock = (SoftBlock) tempObject;
                        softBlock.damage();
                        if (getId() != ID.Bullet_Machine_Gun)
                            handler.addObject(new Explosion(tempObject.x + tempObject.getBounds().width / 2, tempObject.y + tempObject.getBounds().height / 2, ID.Explosion, handler));
                        handler.removeObject(this);
                        break;
                    }
            } catch (Exception ignored) {

            }

        }

        intersectsWithShooter = getBounds().intersects(shooter.getBounds());
    }

    @Override
    public void render(Graphics g) {
        if(!intersectsWithShooter) {
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.translate(x-12, y-5);
            affineTransform.rotate(thetha);
            if(id==ID.Bullet_Cannon)
            ((Graphics2D) g).drawImage(Images.getCannonBullet(), affineTransform, null);
            else if (id==ID.Bullet_Bomb)
                ((Graphics2D) g).drawImage(Images.getBOMB(), affineTransform, null);
            else if (id==ID.Bullet_Machine_Gun)
                ((Graphics2D) g).drawImage(Images.getMachineGunBullet(), affineTransform, null);
        }

        if (handler.gameMode == 3) {
            if (playBulletCannon) {
                new Sounds().playCannonSound();
                playBulletCannon = false;
            }
            if (playBulletMachineGun) {
                new Sounds().playMachineGunSound();
                playBulletMachineGun = false;
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 23, 9);
    }
}
