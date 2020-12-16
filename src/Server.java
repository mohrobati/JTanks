import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Networking {

    /**
     * Opens up the connection for the
     * server and waits for client to connect
     * with the server socket...
     * also ovverrides the close method in Networking class...
     */

    private ServerSocket server;
    private Socket client;

    public Server() throws IOException {
        server = new ServerSocket(7654);
        client = server.accept();
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void close() {
        super.close();
        try {
            client.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
