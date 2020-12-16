import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends Canvas {

    /**
     * UI for the main menu,
     * contains another menu as a inner class
     */

    Windows windows;
    Sounds sounds;

    public MainMenu(Windows windows) {

        this.windows = windows;
        windows.add(this);
        windows.revalidate();
        sounds = new Sounds();
        sounds.playMainMenuTheme();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x >= 750 && x <= 900 && y >= 260 && y <= 300) {
                    windows.add(new MainMenu2());
                    windows.remove(MainMenu.this);
                    windows.revalidate();
                }
                if (x >= 750 && x <= 900 && y >= 301 && y <= 350) {
                    Game game = new Game(windows, 2,false,1, false);
                    windows.remove(MainMenu.this);
                    windows.revalidate();
                    sounds.getClip().close();
                }

                if (x >= 750 && x <= 900 && y >= 351 && y <= 400) {
                    Game game = new Game(windows, 3,false,1, false);
                    windows.remove(MainMenu.this);
                    windows.revalidate();
                    sounds.getClip().close();
                }
                if (x >= 750 && x <= 900 && y >= 401 && y <= 442) {
                    MapEditor mapEditor = new MapEditor(windows);
                    windows.add(mapEditor);
                    windows.remove(MainMenu.this);
                    windows.revalidate();
                    sounds.getClip().close();
                }
                if (x >= 750 && x <= 900 && y >= 443 && y <= 500)
                    System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(Images.getMainMenu(), 0, 0, null);
    }

    private class MainMenu2 extends Canvas {

        public MainMenu2() {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    if (x >= 760 && x <= 930 && y >= 260 && y <= 310) {
                        Game game = new Game(windows, 1,false,1, true);
                        windows.remove(MainMenu2.this);
                        windows.revalidate();
                        sounds.getClip().close();
                    }
                    if (x >= 760 && x <= 930 && y >= 310 && y <= 350) {
                        Game game = new Game(windows, 1,false,1, false);
                        windows.remove(MainMenu2.this);
                        windows.revalidate();
                        sounds.getClip().close();
                    }
                    if (x >= 760 && x <= 930 && y >= 350 && y <= 396) {
                        Game game = new Game(windows, 1,false,2, false);
                        windows.remove(MainMenu2.this);
                        windows.revalidate();
                        sounds.getClip().close();
                    }
                    if (x >= 760 && x <= 930 && y >= 396 && y <=444) {
                        Game game = new Game(windows, 1,false,3, false);
                        windows.remove(MainMenu2.this);
                        windows.revalidate();
                        sounds.getClip().close();
                    }
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(Images.getMainMenu2(),0,0,null);
        }
    }
}
