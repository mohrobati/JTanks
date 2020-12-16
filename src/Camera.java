/**
 * The camera follows your tank.
 * It is used to tell the screen where to show.
 */
public class Camera {

    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(Tank tank) {

        int xx = (tank.getX() * 2 + tank.getMouseX()) / 3;
        int yy = (tank.getY() * 2 + tank.getMouseY()) / 3;

        x += ((xx - x) - 1000/2) * 0.05f;
        y += ((yy - y) - 563/2) * 0.05f;

        if (x < 0) x = 0;
        if (x > 20 * 100 - (1000 - 6)) x = 20 * 100 - (1000 - 6);
        if (y < 0) y = 0;
        if (y > 20 * 100 - (563 - 29)) y = 20 * 100 - (563 - 29);
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
