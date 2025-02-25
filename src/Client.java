import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    private int port;
    private InetAddress serverAddress;
    private Socket socket;
    private InputStream is;
    private Scanner streamIn;
    private OutputStream os;
    private PrintWriter streamOut;

    public Client(int port) {
        this.port = port;
    }

    public void connetti() {
        try {
            serverAddress = InetAddress.getLocalHost();
            System.out.println("Indirizzo del server trovato!");
            socket = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            System.out.println(BLUE + "Socket client: " + socket.getLocalSocketAddress() + RESET);
            System.out.println("Socket server: " + socket.getRemoteSocketAddress());

            os = socket.getOutputStream();
            streamOut = new PrintWriter(os);
            streamOut.flush();

            is = socket.getInputStream();
            streamIn = new Scanner(is);
        } catch (ConnectException e) {
            System.err.println("Server non disponibile!");
        } catch (IOException e) {
            System.err.println("Errore di I/O");
        }
    }

    public String leggi() {
        return streamIn.nextLine();
    }

    public void scrivi(String messaggio) {
        streamOut.println(messaggio);
        streamOut.flush();
    }

    public void chiudi() {
        try {
            if (socket != null) {
                socket.close();
                System.out.println("Socket distrutto");
            }
            if (streamIn != null) {
                streamIn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
