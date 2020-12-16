import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

public class Handler implements Serializable {

    /**
     * most notably holds a linked list of GameObjects
     * and ticks and renders all the objects
     * while looping around them
     */

    private LinkedList<GameObject> objects = new LinkedList<>();
    private Tank clientTank;
    public boolean tank1IsAlive;
    public boolean tank2IsAlive;
    private int currentLevel;
    public int difficulty;
    public static Networking networking;
    public static int gameMode;
    public boolean won;

    public Handler(int gameMode ,int currentLevel, int difficulty) {
        this.currentLevel = currentLevel;
        this.difficulty = difficulty;
        Handler.gameMode = gameMode;
        tank1IsAlive = true;
        if (gameMode == 2)
            tank2IsAlive = true;
    }

    public void tick() {
        if (clientTank != null && tank2IsAlive)
            clientTank.tick();
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId() != ID.Player || tank1IsAlive)
                objects.get(i).tick();
        }
    }

    public void render(Graphics g) {
        if (clientTank != null && tank2IsAlive)
            clientTank.render(g);
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId() != ID.Player || tank1IsAlive)
                objects.get(i).render(g);
        }
    }

    /**
     * reset all the flags of the server
     * to inform client not to play same songs over and over
     */

    public void resetSoundPlayFlags() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            if (tempObject.getId() == ID.Player) {
                Tank tank = (Tank) tempObject;
                tank.setPlayHeal(false);
                tank.setPlayReload(false);
                tank.setPlayUpgrade(false);
            }
            if (tempObject.getId() == ID.Bullet_Cannon)
                ((Bullet) tempObject).setPlayBulletCannon(false);

            if (tempObject.getId() == ID.Bullet_Machine_Gun)
                ((Bullet) tempObject).setPlayBulletMachineGun(false);

            if (tempObject.getId() == ID.Explosion)
                ((Explosion) tempObject).setPlayExplosion(false);
        }

        clientTank.setPlayHeal(false);
        clientTank.setPlayReload(false);
        clientTank.setPlayUpgrade(false);
    }

    public void addObject(GameObject tmpObject) {
        objects.add(tmpObject);
    }

    public void removeObject(GameObject tmpObject) {
        objects.remove(tmpObject);
    }

    public void setClientTank(Tank clientTank) {
        this.clientTank = clientTank;
    }

    public Tank getClientTank() {
        return clientTank;
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }

    public void nextLevel() {
        currentLevel++;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
