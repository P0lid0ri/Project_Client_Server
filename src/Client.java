import java.io.IOException;
import java.io.*;
import java.net.*;

public class Client {
    private String nome;
    private Socket socket;
    private String colore;
    private OutputStream outputStream;
    private PrintWriter out;




    public Client(String nome) {
        this.nome = nome;
    }
    public Client(String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
    }

    public void connetti(String nomeServer, int portaServer) {
        try {
            //Crea una connessione al server
            socket = new Socket(nomeServer, portaServer);
            System.out.println("Connessione al server " + nomeServer + " sulla porta " + portaServer + " riuscita.");
        } catch (IOException e) {
            System.err.println("Errore nella connessione al server");
        }
    }


    public void scrivi(String messaggio) {
        try {
            // Ottieni il flusso di output del socket
            outputStream = socket.getOutputStream();
        }
        catch (IOException e) {
            System.err.println("Errore nella connessione al server");
        }
        //PrintWriter per inviare una stringa tramite il flusso
        out = new PrintWriter(outputStream, true);  //true per auto-flush(svuota il buffer e invia)

        out.println(messaggio);
    }

    public void inpTastiera(){
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput=null;
        while (true) {
            try {
                if (!((userInput = stdIn.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            scrivi(userInput);
        }
    }
}