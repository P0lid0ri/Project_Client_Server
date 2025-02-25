public class MainClient {
    public static void main(String[] args) {
        Client client = new Client(1906);
        client.connetti();

        String messaggioIn = client.leggi();
        System.out.println("Messaggio del server: " + messaggioIn);

        String messaggioOut = "Eccomi!";
        client.scrivi(messaggioOut);

        client.chiudi();
    }
}
