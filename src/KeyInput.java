import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

public class KeyInput extends KeyAdapter {

    /**
     * Reads the events of the keyboard from the player
     * and sets needed field for the movements
     * also checks for the cheat codes...
     */

    Tank tank;
    Game game;
    CheatCode cannon;
    CheatCode health;
    CheatCode machineGun;

    public KeyInput(Tank tank , Game game) {
        this.tank = tank;
        this.game = game;
        cannon = new CheatCode(new int[]{KeyEvent.VK_C, KeyEvent.VK_A,KeyEvent.VK_N,KeyEvent.VK_N,KeyEvent.VK_O,KeyEvent.VK_N});
        health = new CheatCode(new int[]{KeyEvent.VK_H,KeyEvent.VK_E,KeyEvent.VK_A,KeyEvent.VK_L,KeyEvent.VK_T,KeyEvent.VK_H});
        machineGun = new CheatCode(new int[]{KeyEvent.VK_M,KeyEvent.VK_A,KeyEvent.VK_C,KeyEvent.VK_H,KeyEvent.VK_I,KeyEvent.VK_N,
                KeyEvent.VK_E,KeyEvent.VK_G,KeyEvent.VK_U,KeyEvent.VK_N});
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP)
            tank.setUp(true);
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            tank.setDown(true);
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            tank.setRight(true);
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            tank.setLeft(true);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            game.stop();
            game.escape();
        }
        if(cannon.checkCode(e.getKeyCode())) tank.setAmmoCannon(tank.getAmmoCannon() + 50);
        if(machineGun.checkCode(e.getKeyCode())) tank.setAmmoMachineGun(tank.getAmmoMachineGun() + 100);
        if(health.checkCode(e.getKeyCode())) tank.setHp(tank.fullHp);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP)
            tank.setUp(false);
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            tank.setDown(false);
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            tank.setRight(false);
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            tank.setLeft(false);
    }

}
