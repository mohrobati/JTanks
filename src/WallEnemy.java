
import java.awt.*;
import java.awt.geom.AffineTransform;

public class WallEnemy extends Enemy {

    /**
     * A type of enemy which exists in two
     * orientations, is static and attached to wall
     */

    private Tank playerTank1;
        private Tank playerTank;

        private Orientation orientation;
        private int counter=0;

        public WallEnemy(int x, int y, ID id, Handler handler, Tank playerTank1, Orientation orientation, boolean bonus) {
            super(x, y, id, handler,bonus);
            this.playerTank1 = playerTank1;
            this.playerTank = playerTank1;
            this.orientation = orientation;
            hp = 200;
        }

        @Override
        public void tick() {

            Tank playerTank2 = handler.getClientTank();

            if(handler.tank1IsAlive && getBigBounds().intersects(playerTank1.getBounds())){
                playerTank = playerTank1;
                if(counter==1 || counter == 5 || counter == 9 || counter == 13) {
                    if(orientation == Orientation.VERTICAL)
                    handler.addObject(new Bullet(x + 30, y + 60, ID.Bullet_Machine_Gun, handler, playerTank1.x+50, playerTank1.y+50, this));
                    else handler.addObject(new Bullet(x + 60, y + 30, ID.Bullet_Machine_Gun, handler, playerTank1.x+50, playerTank1.y+50, this));

                }
                if(counter==60) counter=0;
                counter++;
            }
            else if (handler.tank2IsAlive && getBigBounds().intersects(playerTank2.getBounds())) {
                playerTank = playerTank2;
                if(counter==1 || counter == 5 || counter == 9 || counter == 13) {
                    if(orientation == Orientation.VERTICAL)
                        handler.addObject(new Bullet(x + 30, y + 60, ID.Bullet_Machine_Gun, handler, playerTank2.x+50, playerTank2.y+50, this));
                    else handler.addObject(new Bullet(x + 60, y + 30, ID.Bullet_Machine_Gun, handler, playerTank2.x+50, playerTank2.y+50, this));

                }
                if(counter==60) counter=0;
                counter++;
            }
            damage(50,40);
        }

        @Override
        public void render(Graphics g) {
            if(orientation == Orientation.HORIZONTAL) {
                g.drawImage(Images.getWallEnemyH(), x, y, null);
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.translate(x + 50, y + 20);
                affineTransform.rotate(Math.atan2((playerTank.y + 50 - 12) - (y + 20), playerTank.x + 50 - (x + 50)));
                affineTransform.translate(-30, -50);
                ((Graphics2D) g).drawImage(Images.getGun5(), affineTransform, null);
            } else {
                g.drawImage(Images.getWallEnemyV(), x, y, null);
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.translate(x + 20, y + 50);
                affineTransform.rotate(Math.atan2((playerTank.y + 50 - 12) - (y + 50), playerTank.x + 50 - (x + 20)));
                affineTransform.translate(-28, -48);
                ((Graphics2D) g).drawImage(Images.getGun5(), affineTransform, null);
            }
        }

        @Override
        public Rectangle getBounds() {
            if(orientation == Orientation.HORIZONTAL) return new Rectangle(x,y,120,60);
            return new Rectangle(x,y,60,120);
        }

        public Rectangle getBigBounds() {
            return new Rectangle(x-400,y-400,800, 800);
        }
    }

