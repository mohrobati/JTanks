import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class MouseInput extends MouseAdapter {

    /**
     * Reads the events that are made by
     * the player mouse, works for machineGun, Cannon
     * holding and clicking...
     */

    private Handler handler;
    private Camera camera;
    private Tank tank;
    private Game game;
    private boolean machineGunStream;
    private boolean cannonDelay = true;
    private boolean tank1IsAlive;
    private boolean tank2IsAlive;

    public MouseInput(Handler handler, Camera camera, Tank tank, Game game) {
        this.handler = handler;
        this.camera = camera;
        this.tank = tank;
        this.game = game;
        tank1IsAlive = handler.tank1IsAlive;
        tank2IsAlive = handler.tank2IsAlive;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int mx = (int) (e.getX() + camera.getX());
            int my = (int) (e.getY() + camera.getY());
            if (game.gameMode == 3 && tank2IsAlive || game.gameMode != 3 && tank1IsAlive) {
                if (tank.getGunStyle() == 1 && cannonDelay) {
                    if (tank.getAmmoCannon() > 0) {
                        Bullet bullet = new Bullet(tank.getX() + 50, tank.getY() + 50, ID.Bullet_Cannon, handler, mx, my, tank);
                        if (game.gameMode == 3)
                            game.setClientBullet(bullet);
                        else
                            handler.addObject(bullet);
                        tank.minusAmmoCannon();
                        cannonDelay = false;
                        new Reminder(0.6);
                    }
                } else if(tank.getGunStyle() == 3) {
                    machineGunStream = true;
                    new Thread(() -> {
                        while (machineGunStream) {
                            if (tank.getAmmoMachineGun() > 0) {
                                Bullet bullet = new Bullet(tank.getX() + 50, tank.getY() + 50, ID.Bullet_Machine_Gun,
                                        handler, tank.getMouseX(), tank.getMouseY(), tank);
                                if (game.gameMode == 3)
                                    game.setClientBullet(bullet);
                                else
                                    handler.addObject(bullet);
                                tank.minusAmmoMachineGun();
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (tank.getGunStyle() == 1)
                tank.setGunStyle(3);
            else tank.setGunStyle(1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        machineGunStream = false;
    }

    private class Reminder {

        Timer timer;

        public Reminder(double seconds) {
            timer = new Timer();
            timer.schedule(new Reminder.RemindTask(), (int) (seconds*1000));
        }

        class RemindTask extends TimerTask {
            public void run() {
                cannonDelay = true;
                timer.cancel();
            }
        }
    }

    public void setTank1IsAlive(boolean tank1IsAlive) {
        this.tank1IsAlive = tank1IsAlive;
    }

    public void setTank2IsAlive(boolean tank2IsAlive) {
        this.tank2IsAlive = tank2IsAlive;
    }
}
