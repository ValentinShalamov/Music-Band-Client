import server.ServerConnector;

import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerConnector connector = new ServerConnector(InetAddress.getByName("localhost"), 8888);
        connector.connectAndStart();
    }
}
