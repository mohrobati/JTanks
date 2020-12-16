import java.io.*;
import java.net.Socket;

/**
 * Opens up a connection for the client side.
 */
public class Client extends Networking {

    private Socket server;

    /**
     * Starts input and output streams for client.
     */
    public Client(String host, int port) throws IOException {
        server = new Socket(host, port);
        out = new ObjectOutputStream(server.getOutputStream());
        in = new ObjectInputStream(server.getInputStream());
    }

    /**
     * Starts input and output streams for client.
     */
    public Client() throws IOException {
        server = new Socket("127.0.0.1", 7654);
        out = new ObjectOutputStream(server.getOutputStream());
        in = new ObjectInputStream(server.getInputStream());
    }

    @Override
    public void close() {
        super.close();
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
