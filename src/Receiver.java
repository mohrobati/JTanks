import java.io.ObjectInputStream;

/**
 * A thread which is always listening to object input stream,
 * and when it receives an object passes it to the game to do the process.
 */
public class Receiver implements Runnable {

    private ObjectInputStream in;
    private boolean isRunning;
    private Thread thread;
    private Game game;

    /**
     * Creates the thread.
     *
     * @param in The object input stream
     * @param game The game which this thread is listening to inform it
     */
    public Receiver(ObjectInputStream in, Game game) {
        this.in = in;
        this.game = game;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the thread.
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Listens and receives objects.
     */
    @Override
    public void run() {
        while (isRunning) {
            try {
                Object received = in.readObject();
                game.receive(received);
            } catch (Exception e) {
                //e.printStackTrace();
                game.connect();
            }
        }
    }
}
