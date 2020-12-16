import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The motion picture that represents explosion
 * as a result of collision of bullets with enemies or other things.
 */
public class Explosion extends GameObject {

    private boolean finished;
    private boolean playExplosion;

    public Explosion(int x, int y, ID id, Handler handler) {
        super(x - 65, y - 90, id, handler);
        new Reminder(1);
        (new Sounds()).playExplosionSound();
        if (handler.gameMode == 2)
            playExplosion = true;
    }

    @Override
    public void tick() {
        if (finished)
            handler.removeObject(Explosion.this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.getEXPLOSION(), x, y, null);

        if (handler.gameMode == 3 && playExplosion) {
            new Sounds().playExplosionSound();
            playExplosion = false;
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public void setPlayExplosion(boolean playExplosion) {
        this.playExplosion = playExplosion;
    }

    private class Reminder {

        Timer timer;

        public Reminder(int seconds) {
            timer = new Timer();
            timer.schedule(new RemindTask(),  seconds*1000);
        }

        class RemindTask extends TimerTask {
            public void run() {
                finished = true;
                timer.cancel();
            }
        }

    }
}
