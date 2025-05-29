public class MainClient {
    public static void main(String[] args) {
        Client client = new Client("Ciao");
        client.connetti("localhost",2000);
        client.inpTastiera();


    }
}
