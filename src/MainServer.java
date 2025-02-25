public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(1906);
        server.attendi();

        String messaggioOut = "Ciao client! Ti aspettavo!";
        server.scrivi(messaggioOut);

        String messaggioIn = server.leggi();
        System.out.println("Messaggio del client: " + messaggioIn);

        server.chiudi();
        server.termina();
    }
}
