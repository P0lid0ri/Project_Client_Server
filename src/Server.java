import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private int porta;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private InputStream is;
    private InputStream in;
    private OutputStream out;
    public static final String giallo = "\u001B[33m";
    public static final String reset = "\u001B[0m";

    public Server(int porta) {
        this.porta = porta;
        try{
            serverSocket=new ServerSocket(porta);
            System.out.println("Connesione Riuscita: "+porta);
        } catch (IOException e){
            e.printStackTrace();
            System.err.println("Errore connesione porta ");
        }

    }

    public Socket attendi() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Connesione ricevuta da :  " + clientSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientSocket;
    }

    public void chiudi () {
        try{
            if (clientSocket != null){
                clientSocket.close();
            }
            if(serverSocket !=null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void leggi() {
        InputStream inputStream = null;
        String message = null;

        try {
            inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((message = reader.readLine()) != null) {
                System.out.println("Messaggio del client: " + giallo + message + reset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


