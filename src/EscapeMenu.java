import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Escape menu is shown when the ESC is pressed
 */
public class EscapeMenu extends Canvas {

    Windows windows;
    Game game;

    public EscapeMenu(Windows windows, Game game) {

        this.windows = windows;
        this.game = game;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x >= 477 && x <= 571 && y >= 255 && y <= 288) {
                    game.start();
                    game.setVisible(true);
                    windows.remove(EscapeMenu.this);
                    windows.revalidate();
                }
                if (x >= 442 && x <= 574 && y >= 306 && y <= 331) {
                    if (game.gameMode != 1)
                        game.stopNetwork();
                    windows.remove(game);
                    windows.remove(EscapeMenu.this);
                    windows.add(new MainMenu(windows));
                    windows.revalidate();
                }
                if (x >= 443 && x <= 578 && y >= 350 && y <= 372) {
                    game.save();
                    System.exit(0);
                }
            }
        });
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(Images.getEscapeMenu(), 0, 0, null);
    }
}
