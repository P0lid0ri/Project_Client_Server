import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream is;
    private Scanner streamIn;
    private OutputStream os;
    private PrintWriter streamOut;

    public Server(int port) {
        this.port = port;
    }

    public void attendi() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server in ascolto sulla porta " + port + ". \n");

            socket = serverSocket.accept();
            System.out.println("Connessione stabilita e richiesta ricevuta!");
            System.out.println("Socket server: " + socket.getLocalSocketAddress());
            System.out.println("Socket client: " + socket.getRemoteSocketAddress());
            System.out.print("\007");
            System.out.flush();

            os = socket.getOutputStream();
            streamOut = new PrintWriter(os);
            is = socket.getInputStream();
            streamIn = new Scanner(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scrivi(String messaggio) {
        System.out.println("Spedisco il messaggio al client!");
        streamOut.println(messaggio);
        streamOut.flush();
    }

    public String leggi() {
        System.out.println("Leggo il messaggio del client!");
        return streamIn.next();
    }

    public void chiudi() {
        try {
            if (socket != null) {
                socket.close();
            }
            System.out.println("Connessione chiusa!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void termina() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
