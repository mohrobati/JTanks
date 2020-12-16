import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

/**
 * It's the main character of the game.
 * The game starts when it comes
 * and ends when it goes...
 */
public class Tank extends GameObject {

    private boolean up = false, down = false, right = false, left = false;

    private int hp = 100, ammoCannon = 100, ammoMachineGun = 200;

    public final int fullHp;

    private int mouseX;
    private int mouseY;

    private int gunStyle = 1 ;

    private boolean isUpgradedForCannon;
    private boolean isUpgradedForMachineGun;

    private int rotationCounter=8;
    private int preState = 1;
    private int state = 1;

    private boolean isShieldOn;

    private boolean playReload;
    private boolean playUpgrade;
    private boolean playHeal;

    public static Game game;

    // Its usage is only in client mode
    public Tank(int x, int y, ID id) {
        super(x, y, id, null);
        fullHp = 300;
    }

    public Tank(int x, int y, ID id , Handler handler) {
        super(x, y, id, handler);
        if(handler.difficulty==1) hp = 300;
        if(handler.difficulty==2) hp = 200;
        if(handler.difficulty==3) hp = 100;

        fullHp = hp;

    }

    /**
     * Defines the location of the tank.
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the current direction of the tank.
     */
    public void setUp(boolean up) {
        if(!this.up && up && state != 4) {
            preState = state;
            state = 2;
            rotationCounter = 0;
        }
        this.up = up;
    }

    /**
     * Sets the current direction of the tank.
     */
    public void setDown(boolean down) {
        if(!this.down && down && state != 2) {
            preState = state;
            state = 4;
            rotationCounter = 0;
        }
        this.down = down;
    }

    /**
     * Sets the current direction of the tank.
     */
    public void setRight(boolean right) {
        if(!this.right && right && state != 3) {
            preState = state;
            state = 1;
            rotationCounter = 0;
        }
        this.right = right;
    }

    /**
     * Sets the current direction of the tank.
     */
    public void setLeft(boolean left) {
        if(!this.left && left && state != 1) {
            preState = state;
            state = 3;
            rotationCounter = 0;
        }
        this.left = left;
    }

    /**
     * @return The current direction of the tank.
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @return The current direction of the tank.
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @return The current direction of the tank.
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @return The current direction of the tank.
     */
    public boolean isUp() {
        return up;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        collision();

        if(up) velY=-4;
        else if(!down) velY=0;

        if(down) velY=4;
        else if(!up) velY=0;

        if(right) velX=4;
        else if(!left) velX=0;

        if(left) velX=-4;
        else if(!right) velX=0;
    }

    /**
     * Searches for any collision with the tank.
     * Enemies, walls or crates for example.
     */
    private void collision() {

        for (int i = 0; i < handler.getObjects().size(); i++) {

            GameObject tempObject = handler.getObjects().get(i);

            if (tempObject.getId() == ID.Block || tempObject.getId() == ID.SoftBlock || tempObject.getId() == ID.Teazel ||
                    (tempObject.getId() == ID.Enemy && !(tempObject instanceof Mine)))
                if (getBounds().intersects(tempObject.getBounds())) {
                    x -= velX;
                    y -= velY;
                }

            if (tempObject.getId() == ID.Player && tempObject != this)
                if (getBounds().intersects(tempObject.getBounds())) {
                    x -= velX;
                    y -= velY;
                }

            if (tempObject.getId() == ID.Crate_Cannon) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    ammoCannon += 50;
                    handler.removeObject(tempObject);
                    (new Sounds()).playReloadSound();
                    if (handler.gameMode == 2)
                        playReload = true;
                }
            } else if (tempObject.getId() == ID.Crate_Machine_Gun) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    ammoMachineGun += 100;
                    handler.removeObject(tempObject);
                    (new Sounds()).playReloadSound();
                    if (handler.gameMode == 2)
                        playReload = true;
                }
            } else if (tempObject.getId() == ID.Crate_Repair) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hp = fullHp;
                    handler.removeObject(tempObject);
                    (new Sounds()).playUpgradeSound();
                    if (handler.gameMode == 2)
                        playUpgrade = true;
                }
            } else if (tempObject.getId() == ID.Upgrade_Star) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if(gunStyle==1) isUpgradedForCannon=true;
                    if(gunStyle==3) isUpgradedForMachineGun=true;
                    handler.removeObject(tempObject);
                    (new Sounds()).playUpgradeSound();
                    if (handler.gameMode == 2)
                        playUpgrade = true;
                }
            } else if (tempObject.getId() == ID.Potion) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    isShieldOn = true;
                    new Reminder(10);
                    handler.removeObject(tempObject);
                    (new Sounds()).playHealSound();
                    if (handler.gameMode == 2)
                        playHeal = true;
                }
            } else if (tempObject.getId() == ID.Final_Key) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                    (new Sounds()).playHealSound();
                    if (handler.gameMode == 2)
                        playHeal = true;
                    if(game.isMapEditor()) {
                        (new Thread(() -> game.stop())).start();
                        game.getWindows().add(new EndMenu(game.getWindows(),true,game));
                        game.getWindows().remove(game);
                        game.getWindows().revalidate();
                    } else {
                        Thread thread = new Thread(() -> game.nextLevel());
                        thread.start();
                    }
                }
            }

            if (tempObject.getId() == ID.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())){
                    if(tempObject instanceof BugEnemy || tempObject instanceof Mine) {
                        if(!isShieldOn)
                        hp = hp - 40;
                        handler.addObject(new Explosion(x + 50, y + 50, ID.Explosion, handler));
                        handler.removeObject(tempObject);
                    }
                }
            }

            if (tempObject.getId() == ID.Bullet_Cannon) {
                if(tempObject.getBounds().intersects(getBounds()))
                if(((Bullet) tempObject).getShooter().getId() != ID.Player) {
                    if(!isShieldOn)
                        hp = hp - 20;
                        handler.addObject(new Explosion(x + 50, y + 50, ID.Explosion, handler));
                        handler.removeObject(tempObject);
                }
            } else if (tempObject.getId() == ID.Bullet_Machine_Gun) {
                if(tempObject.getBounds().intersects(getBounds()))
                if(((Bullet) tempObject).getShooter().getId() != ID.Player) {
                    if(!isShieldOn)
                        hp = hp - 7;
                        handler.removeObject(tempObject);
                }
            } else if (tempObject.getId() == ID.Bullet_Bomb) {
                if (tempObject.getBounds().intersects(getBounds()))
                    if (((Bullet) tempObject).getShooter().getId() != ID.Player) {
                    if(!isShieldOn)
                        hp = hp - 25;
                        handler.addObject(new Explosion(x + 50, y + 50, ID.Explosion, handler));
                        handler.removeObject(tempObject);
                }
            }

        }

        if (hp <= 0) {
            if (this == handler.getClientTank())
                handler.tank2IsAlive = false;
            else
                handler.tank1IsAlive = false;
        }

        if (handler.getClientTank() != null && handler.getClientTank() != this)
            if (getBounds().intersects(handler.getClientTank().getBounds())) {
                x -= velX;
                y -= velY;
            }
    }

    /**
     * Specifies if the client has to play the heal sound.
     */
    public void setPlayHeal(boolean playHeal) {
        this.playHeal = playHeal;
    }

    /**
     * Specifies if the client has to play the reload sound.
     */
    public void setPlayReload(boolean playReload) {
        this.playReload = playReload;
    }

    /**
     * Specifies if the client has to play the upgrade sound.
     */
    public void setPlayUpgrade(boolean playUpgrade) {
        this.playUpgrade = playUpgrade;
    }

    @Override
    public void render(Graphics g) {
        tankRotation(g);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(x+50 ,y+50);
        affineTransform.rotate(Math.atan2((mouseY-12)-(y+50),mouseX-(x+50)));
        affineTransform.translate(-32,-29);
        if(gunStyle==1) {
            ((Graphics2D) g).drawImage(cannonGunPic(), affineTransform, null);
        } else if (gunStyle==3) {
            ((Graphics2D) g).drawImage(machineGunPic(), affineTransform, null);
        }

        if (handler.gameMode == 3) {
            if (playHeal) {
                new Sounds().playHealSound();
                playHeal = false;
            }
            if (playUpgrade) {
                new Sounds().playUpgradeSound();
                playUpgrade = false;
            }
            if (playReload) {
                new Sounds().playReloadSound();
                playReload = false;
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 1,y + 1,98,98);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAmmoCannon() {
        return ammoCannon;
    }

    public int getAmmoMachineGun() {
        return ammoMachineGun;
    }

    public void setAmmoCannon(int ammoCannon) {
        this.ammoCannon = ammoCannon;
    }

    public void setAmmoMachineGun(int ammoMachineGun) {
        this.ammoMachineGun = ammoMachineGun;
    }

    public void minusAmmoCannon() {
        ammoCannon--;
    }

    public void minusAmmoMachineGun() {
        ammoMachineGun--;
    }

    /**
     * Copies the the fields of another Tank object into this one.
     */
    public void copy(Tank tank) {
        this.x = tank.x;
        this.y = tank.y;
        this.hp = tank.hp;
        this.ammoCannon = tank.ammoCannon;
        this.ammoMachineGun = tank.ammoMachineGun;
        this.mouseX = tank.mouseX;
        this.mouseY = tank.mouseY;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getGunStyle() {
        return gunStyle;
    }

    public void setGunStyle(int gunStyle) {
        this.gunStyle = gunStyle;
    }

    /**
     * Gives the picture of the cannon in depend of its type.
     */
    private BufferedImage cannonGunPic() {
        if(isUpgradedForCannon) {
            return Images.getGun2();
        } else {
            return Images.getGun1();
        }
    }

    /**
     * Gives the picture of the cannon in depend of its type.
     */
    private BufferedImage machineGunPic() {
        if(isUpgradedForMachineGun) {
            return Images.getGun6();
        } else {
            return Images.getGun3();
        }
    }

    public boolean isUpgradedForCannon() {
        return isUpgradedForCannon;
    }

    public boolean isUpgradedForMachineGun() {
        return isUpgradedForMachineGun;
    }

    /**
     * Rotates the body of the tanks.
     */
    public void tankRotation(Graphics g) {
        AffineTransform transform = new AffineTransform();
        transform.translate(x+50, y+50);
        if(preState==1) {
            if(state==2){
                if(rotationCounter!=8) {
                    transform.rotate(rotationCounter*Math.PI / 16);
                    rotationCounter++;
                } else {
                    transform.rotate(Math.PI / 2);
                }
            }
            else if(state==4) {
                if(rotationCounter!=8) {
                    transform.rotate(rotationCounter*(-Math.PI) / 16);
                    rotationCounter++;
                } else {
                    transform.rotate(-Math.PI / 2);
                }
            }
        } else if (preState==2) {
            if(state == 1){
                if(rotationCounter!=8) {
                    transform.rotate(rotationCounter*(-Math.PI) / 16);
                    rotationCounter++;
                } else {
                    transform.rotate(-Math.PI / 2);
                }
            }
            else if(state == 3){
                if(rotationCounter!=8) {
                    transform.rotate(rotationCounter*Math.PI / 16);
                    rotationCounter++;
                } else {
                    transform.rotate(Math.PI / 2);
                }
            }
        } else if (preState==3) {
            if(state == 2) {
                if(rotationCounter!=8) {
                    transform.rotate(rotationCounter*(-Math.PI) / 16);
                    rotationCounter++;
                } else {
                    transform.rotate(-Math.PI / 2);
                }
            }
            else if(state == 4) {
                if(rotationCounter!=8) {
                    transform.rotate(rotationCounter*Math.PI / 16);
                    rotationCounter++;
                } else {
                    transform.rotate(Math.PI / 2);
                }
            }
        } else if (preState==4) {
            if(state == 1) {
                if(rotationCounter!=8) {
                    transform.rotate(rotationCounter*Math.PI / 16);
                    rotationCounter++;
                } else {
                    transform.rotate(Math.PI / 2);
                }
            }
            else if(state == 3) {
                if(rotationCounter!=8) {
                    transform.rotate(rotationCounter*(-Math.PI)/ 16);
                    rotationCounter++;
                } else {
                    transform.rotate(-Math.PI / 2);
                }
            }
        }
        transform.translate(-50,-50);

        if(!(isUp() || isDown() || isRight() || isLeft())){
            if(isShieldOn) {
                switch (preState) {
                    case 1:
                        ((Graphics2D) g).drawImage(Images.getTankShield1(), transform, null);
                        break;
                    case 2:
                        ((Graphics2D) g).drawImage(Images.getTankShield2(), transform, null);
                        break;
                    case 3:
                        ((Graphics2D) g).drawImage(Images.getTankShield3(), transform, null);
                        break;
                    case 4:
                        ((Graphics2D) g).drawImage(Images.getTankShield4(), transform, null);
                        break;
                }
            } else {
                switch (preState) {
                    case 1:
                        ((Graphics2D) g).drawImage(Images.getTank1(), transform, null);
                        break;
                    case 2:
                        ((Graphics2D) g).drawImage(Images.getTank2(), transform, null);
                        break;
                    case 3:
                        ((Graphics2D) g).drawImage(Images.getTank3(), transform, null);
                        break;
                    case 4:
                        ((Graphics2D) g).drawImage(Images.getTank4(), transform, null);
                        break;
                }
            }
        } else {
            if(isShieldOn) {
                switch (preState) {
                    case 1:
                        ((Graphics2D) g).drawImage(Images.getTankShieldAnim1(), transform, null);
                        break;
                    case 2:
                        ((Graphics2D) g).drawImage(Images.getTankShieldAnim2(), transform, null);
                        break;
                    case 3:
                        ((Graphics2D) g).drawImage(Images.getTankShieldAnim3(), transform, null);
                        break;
                    case 4:
                        ((Graphics2D) g).drawImage(Images.getTankShieldAnim4(), transform, null);
                        break;
                }
            } else {
                switch (preState) {
                    case 1:
                        ((Graphics2D) g).drawImage(Images.getTankAnim1(), transform, null);
                        break;
                    case 2:
                        ((Graphics2D) g).drawImage(Images.getTankAnim2(), transform, null);
                        break;
                    case 3:
                        ((Graphics2D) g).drawImage(Images.getTankAnim3(), transform, null);
                        break;
                    case 4:
                        ((Graphics2D) g).drawImage(Images.getTankAnim4(), transform, null);
                        break;
                }
            }
        }
    }

    private class Reminder {

        Timer timer;

        public Reminder(int seconds) {
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds*1000);
        }

        class RemindTask extends TimerTask {
            public void run() {
                isShieldOn = false;
                timer.cancel();
            }
        }

    }
}
