import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Networking {

    /**
     * A super class for client and server and the main
     * base for opening up the connection...
     * note that the program works with ObjectStreams
     * and server and client correspond in Objects...
     */

    protected ObjectOutputStream out;
    protected ObjectInputStream in;
    private Game game;
    private Receiver receiver;
    //private Transmitter transmitter;

    /**
     * closes the connection
     */

    public void close() {
        //if (receiver != null)
            receiver.stop();
        //transmitter.stop();
    }

    /**
     *
     * @param obj object we want to export
     */

    public void export(Object obj) {
        //transmitter.transmit(obj);

        try {
            out.writeObject(obj);
            out.flush();
            out.reset();
        } catch (IOException e) {

           // e.printStackTrace();

            try {
                //System.out.println("Exception Handled...");
                Thread.sleep(10);
                //out.reset();
            } catch (InterruptedException es) {
                //e.printStackTrace();
            }

        }
        catch (Exception e) {
            //e.printStackTrace();
            game.connect();
        }
    }

    public void start(Game game) {
        this.game = game;
        receiver = new Receiver(in, game);
        //transmitter = new Transmitter(out, game);
    }
}
