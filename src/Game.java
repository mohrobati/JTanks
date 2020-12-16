import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Game extends Canvas implements Runnable {

    /**
     * This class is a canvas which will draw all the objects
     * and maps
     *
     * most notably this class is a different Thread
     * which in the run method holds the game loop
     */

    private boolean isRunning;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private Tank tank;
    private Windows windows;
    private Bullet clientBullet;

    /*
    1 for one player
    2 for two player (this computer as sever)
    3 for two player (this computer as client)
     */
    public int gameMode;

    private Networking networking;
    private MouseInput mouseInput;

    private BufferedImage level1 = null;
    private BufferedImage level1_plants = null;
    private BufferedImage level2 = null;
    private BufferedImage level2_plants = null;
    private BufferedImage level3 = null;
    private BufferedImage level3_plants = null;

    private int difficulty;

    private Sounds sounds;

    private boolean mapEditor;

    /**
     *
     * @param windows main frame
     * @param gameMode singlePlayer,Server, Client
     * @param mapEditor is game runs as mapEditor or not
     * @param difficulty easy medium hard
     * @param load loads if game goes over continue
     */

    public Game(Windows windows, int gameMode, boolean mapEditor, int difficulty, boolean load) {

        this.gameMode = gameMode;
        this.windows = windows;
        this.difficulty = difficulty;
        this.mapEditor = mapEditor;

        sounds = new Sounds();

        connect();

        windows.add(this);

        if (load) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("res/handler"))) {
                handler = (Handler) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                //e.printStackTrace();
            }
        }
        else
            handler = new Handler(gameMode ,1, difficulty);
        Handler.networking = networking;
        Handler.gameMode = gameMode;

        camera = new Camera(0, 0);

        BufferedImageLoader loader = new BufferedImageLoader();
        if(!mapEditor) {
            level1 = loader.loadImage("/level1.png");
            level1_plants = loader.loadImage("/level1_plants.png");
            level2 = loader.loadImage("/level2.png");
            level2_plants = loader.loadImage("/level2_plants.png");
            level3 = loader.loadImage("/level3.png");
            level3_plants = loader.loadImage("/level3_plants.png");
        }
        else {
            try {
                level1 = ImageIO.read(new File("res/mapEditor.png"));
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }

        //Images.importImages();

        if (load) {
            for (GameObject go : handler.getObjects())
                if (go.getId() == ID.Player)
                    tank = (Tank) go;
        }
        else
            tank = new Tank(0, 0, ID.Player, handler);

        Tank.game = this;

        if (!load) {
            loadLevel(level1);
            if (!mapEditor)
                loadLevel(level1_plants);
        }

        this.addKeyListener(new KeyInput(tank,this));
        this.addMouseListener(mouseInput = new MouseInput(handler, camera, tank, this));


        start();

        if (gameMode != 1)
            networking.start(this);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor c = toolkit.createCustomCursor(Images.getAIM() , new Point(this.getX(),
                this.getY()), "img");
        this.setCursor (c);

    }

    /**
     * method for going nextLevel
     */

    public void nextLevel() {

        if (handler.getCurrentLevel() == 3) {
            handler.won = true;
            return;
        }

        stop();

        handler.getObjects().clear();

        if (handler.getCurrentLevel() == 1) {
            loadLevel(level2);
            loadLevel(level2_plants);
        }

        else if (handler.getCurrentLevel() == 2) {
            loadLevel(level3);
            loadLevel(level3_plants);
        }

        handler.nextLevel();
        start();
    }

    public void stopNetwork() {
        networking.close();
    }

    /**
     * connects server and client
     */

    public void connect() {

        if (networking != null)
            networking.close();

        if (gameMode == 2) {
            try {
                //JOptionPane.showMessageDialog(windows,"Starting the Connection...","Server",1);
                System.out.println("Starting the Connection...");
                networking = new Server();
            } catch (IOException e) {
                //JOptionPane.showMessageDialog(windows,"Connection Failed!","Server",1);
                //e.printStackTrace();
                connect();
            }
        }

        if (gameMode == 3) {
            try {
                //String host = JOptionPane.showInputDialog(windows,"Please Enter Server IP : ","127.0.0.1");
                //String port = JOptionPane.showInputDialog(windows,"Please Enter Server Port : ","7654");
                //JOptionPane.showMessageDialog(windows,"Starting the Connection...","Client",1);
                System.out.println("Starting the Connection...");
                //networking = new Client(host,Integer.parseInt(port));
                networking = new Client();
            } catch (IOException e) {
                //JOptionPane.showMessageDialog(windows,"Connection Failed!","Client",1);
                //e.printStackTrace();
                connect();
            }
        }

        if (gameMode != 1 && isRunning)
            networking.start(this);

    }

    /**
     * starting the thread
     */

    public void start(){
        isRunning=true;
        thread = new Thread(this);
        thread.start();
        sounds.playMissionTheme();
    }

    /**
     * stops the thread...
     */

    public void stop(){
        isRunning=false;
        try {
            thread.join();
            sounds.getClip().close();
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }

    /**
     * main tick method, ticks the handler
     * so it ticks every game object...
     */

    public void tick() {

        mouseInput.setTank1IsAlive(handler.tank1IsAlive);
        mouseInput.setTank2IsAlive(handler.tank2IsAlive);

        tank.setMouseX((int) (MouseInfo.getPointerInfo().getLocation().x - windows.getX() + camera.getX()));
        tank.setMouseY((int) (MouseInfo.getPointerInfo().getLocation().y - windows.getY() + camera.getY()));

        camera.tick(tank);
        if (gameMode != 3)
            handler.tick();

        if (gameMode == 2) {
            networking.export(handler);
            handler.resetSoundPlayFlags();
        }

        if (gameMode == 3) {
            networking.export(tank);
            if (clientBullet != null) {
                networking.export(clientBullet);
                clientBullet = null;
            }
        }
        /*EscapeMenu escapeMenu = new EscapeMenu();
        windows.add(escapeMenu);
        escapeMenu.setVisible(true);
        setVisible(false);
        windows.revalidate();*/

        if(!handler.tank1IsAlive && !handler.tank2IsAlive){
            (new Thread(this::stop)).start();
            windows.add(new EndMenu(windows,false,this));
            windows.remove(this);
            windows.revalidate();
        }

        if(handler.won) {
            (new Thread(this::stop)).start();
            windows.add(new EndMenu(windows,true,this));
            windows.remove(this);
            windows.revalidate();
        }

    }

    public void setClientBullet(Bullet clientBullet) {
        this.clientBullet = clientBullet;
    }

    public void receive(Object received) {

        if (!isRunning)
            return;

        // This only happens in Client Game Mode.
        if (received instanceof Handler) {
            handler = (Handler) received;
            tank.copy(handler.getClientTank());
        }

        // This only happens in Client Game Mode.
/*        if (received instanceof Audio) {
            switch (((Audio) received).getAudio()) {
                case "cannon":
                    (new Sounds()).playCannonSound();
                    break;
                case "machineGun":
                    (new Sounds()).playMachineGunSound();
                    break;
                case "reload":
                    (new Sounds()).playReloadSound();
                    break;
                case "upgrade":
                    (new Sounds()).playUpgradeSound();
                    break;
                case "heal":
                    (new Sounds()).playHealSound();
                    break;
                case "explosion":
                    (new Sounds()).playExplosionSound();
            }
        }*/

        // This only happens in Server Game Mode.
        if (received instanceof Tank) {
            Tank clientTank = (Tank) received;
            handler.getClientTank().setDown(clientTank.isDown());
            handler.getClientTank().setLeft(clientTank.isLeft());
            handler.getClientTank().setRight(clientTank.isRight());
            handler.getClientTank().setUp(clientTank.isUp());
            handler.getClientTank().setMouseX(clientTank.getMouseX());
            handler.getClientTank().setMouseY(clientTank.getMouseY());
            handler.getClientTank().setGunStyle(clientTank.getGunStyle());
        }

        // This only happens in Server Game Mode.
        if (received instanceof Bullet) {
            Bullet bullet = (Bullet) received;
            bullet.setHandler(handler);
            handler.addObject(bullet);
            if (bullet.getId() == ID.Bullet_Machine_Gun) {
                (new Sounds()).playMachineGunSound();
                handler.getClientTank().minusAmmoMachineGun();
            }
            if (bullet.getId() == ID.Bullet_Cannon) {
                (new Sounds()).playCannonSound();
                handler.getClientTank().minusAmmoCannon();
            }
        }
    }

    /**
     * renders the canvas, means all the objects
     * using bufferStrategy
     */

    public void render() {

        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy==null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        //////////////

        g2d.translate(-camera.getX(), -camera.getY());

        for (int xx = 0; xx < 64 * 32; xx += 32) {
            for (int yy = 0; yy < 64 * 32; yy += 32) {
                g.drawImage(Images.getSOIL(), xx, yy, null);
            }
        }

        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());

        g.setColor(Color.gray);
        g.fillRect(5, 5, 400, 32);
        g.setColor(Color.green);
        if(difficulty==1)
        g.fillRect(5, 5, tank.getHp() * 400/300 , 32);
        else if(difficulty==2)
            g.fillRect(5, 5, tank.getHp() * 400/200 , 32);
        else if(difficulty==3)
            g.fillRect(5, 5, tank.getHp() * 400/100 , 32);
        g.setColor(Color.black);
        g.drawRect(5, 5, 400, 32);

        g.drawImage(Images.getCannonBulletLogo(),5,40,null);
        g.setColor(Color.white);
        g.drawString("" + tank.getAmmoCannon(), 20, 105);
        g.drawImage(Images.getMachineGunBulletLogo(),60,30,null);
        g.setColor(Color.white);
        g.drawString("" + tank.getAmmoMachineGun(), 75, 105);

        //////////////

        g.dispose();
        bufferStrategy.show();
    }

    // Loading the currentLevel
    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        Random random = new Random();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (gameMode != 3) {

                    if (red == 255 && green == 0 && blue == 0)
                        handler.addObject(new Block(xx * 100, yy * 100, ID.Block, handler));

                    if (red == 0 && green == 0 && blue == 255) {
                        tank.setLocation(xx * 100, yy * 100);
                        handler.addObject(tank);
                    }

                    if (red == 200 && green == 0 && blue == 0)
                        handler.addObject(new SoftBlock(xx * 100, yy * 100, ID.SoftBlock, handler, random.nextBoolean()));

                    if (red == 50 && green == 0 && blue == 0)
                        handler.addObject(new Teazel(xx * 100, yy * 100, ID.Teazel, handler));

                    if (red == 0 && green == 255 && blue == 0)
                        handler.addObject(new BugEnemy(xx * 100, yy * 100, ID.Enemy, handler,tank,random.nextBoolean()));

                    if (red == 0 && green == 254 && blue == 0)
                                handler.addObject(new StaticEnemy(xx * 100, yy * 100, ID.Enemy,handler,tank,random.nextBoolean()));

                    if (red == 0 && green == 253 && blue == 0)
                        handler.addObject(new TankEnemy(xx * 100, yy * 100, ID.Enemy,handler,tank,random.nextBoolean()));

                    if (red == 0 && green == 252 && blue == 0)
                        handler.addObject(new MachineGunEnemy(xx * 100, yy * 100, ID.Enemy,handler,tank,random.nextBoolean()));

                    if (red == 0 && green == 251 && blue == 0)
                        handler.addObject(new WallEnemy(xx * 100, yy * 100, ID.Enemy,handler,tank, Orientation.HORIZONTAL,random.nextBoolean()));

                    if (red == 0 && green == 250 && blue == 0)
                        handler.addObject(new WallEnemy(xx * 100, yy * 100, ID.Enemy,handler,tank,Orientation.VERTICAL,random.nextBoolean()));

                    if (red == 0 && green == 249 && blue == 0)
                        handler.addObject(new Mine(xx * 100, yy * 100, ID.Enemy,handler,random.nextBoolean()));

                    if (red == 0 && green == 255 && blue == 255)
                        handler.addObject(new CannonCrate(xx * 100, yy * 100, ID.Crate_Cannon, handler));

                    if (red == 0 && green == 255 && blue == 210)
                        handler.addObject(new MachineGunCrate(xx * 100, yy * 100, ID.Crate_Machine_Gun, handler));

                    if (red == 0 && green == 255 && blue == 150)
                        handler.addObject(new RepairCrate(xx * 100, yy * 100, ID.Crate_Repair, handler));

                    if (red == 0 && green == 255 && blue == 50)
                        handler.addObject(new UpgradeStar(xx * 100, yy * 100, ID.Upgrade_Star, handler));

                    if (red == 0 && green == 255 && blue == 10)
                        handler.addObject(new Potion(xx * 100, yy * 100, ID.Potion, handler));

                    if (red == 255 && green == 255 && blue == 0)
                        handler.addObject(new FinalKey(xx * 100, yy * 100, ID.Final_Key, handler));

                    if (red == 100 && green == 100 && blue == 100)
                        handler.addObject(new Plant(xx * 100, yy * 100, ID.Plant, handler,1));

                    if (red == 100 && green == 101 && blue == 100)
                        handler.addObject(new Plant(xx * 100, yy * 100, ID.Plant, handler,2));

                }

                if (red == 255 && green == 0 && blue == 255 ) {
                    if (gameMode == 2)
                        handler.setClientTank(new Tank(xx * 100, yy * 100, ID.Player, handler));
                    if (gameMode == 3)
                        tank = new Tank(xx * 100, yy * 100, ID.Player);
                }
            }

        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ticks_ns = 1000000000/amountOfTicks;
        double ticks_delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            ticks_delta+=(now-lastTime)/ticks_ns;
            lastTime = now;
            while (ticks_delta>=1) {
                tick();
                ticks_delta--;
            }
            if(!isRunning)
                break;
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
    }

    /**
     * making the escape menu and stoping the game...
     */

    public void escape() {
        EscapeMenu escapeMenu = new EscapeMenu(windows,this);
        windows.add(escapeMenu);
        //escapeMenu.setVisible(true);
        setVisible(false);
        windows.revalidate();
    }

    /**
     * saving the game
     */

    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("res/handler"))) {
            out.writeObject(handler);
            out.flush();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public boolean isMapEditor() {
        return mapEditor;
    }

    public Windows getWindows() {
        return windows;
    }

}
