import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

public abstract class GameObject implements Serializable {

    /**
     * All the things shown in the game would be game object
     * and a subclass of this class...
     * Abstract class, with three abstract methods...
     */

    /**
     *  x,y -> coordinates of the object...
     *  ID -> ID of the object to declare type
     *  Handler -> just to access the handler for the needed reasons
     */

    protected int x,y;
    protected float velX = 0 , velY = 0;
    protected ID id;
    protected Handler handler;
    protected boolean aiIntersection;
    protected boolean rangeIntersection;

    public GameObject(int x, int y, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public Rectangle getUpSide() {
        return null;
    }

    public Rectangle getDownSide() {
        return null;
    }

    public Rectangle getLeftSide() {
        return null;
    }

    public Rectangle getRightSide() {
        return null;
    }

    public Rectangle getRange() {
        return new Rectangle(x-500,y-500,1000,1000);
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }


}
