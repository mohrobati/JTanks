import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The menu which has to be shown when the game ends;
 * whether successfully or not.
 */
public class EndMenu extends Canvas {

    private boolean isSuccessful;
    private Windows windows;
    private Game game;

    public EndMenu(Windows windows, boolean isSuccessful,Game game) {
        this.windows = windows;
        this.isSuccessful = isSuccessful;
        this.game = game;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x >= 390 && x <= 600 && y >= 310 && y <= 390) {
                    windows.add(new MainMenu(windows));
                    windows.remove(EndMenu.this);
                    windows.revalidate();
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isSuccessful)
            g.drawImage(Images.getEndMenu1(), 0, 0, null);
        else g.drawImage(Images.getEndMenu2(), 0, 0, null);
    }
}
