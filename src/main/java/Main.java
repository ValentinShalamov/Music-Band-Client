import client.ConsoleUI;
import server.ServerConnector;

import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {

        try(ServerConnector connector = new ServerConnector(InetAddress.getByName("193.124.115.131"), 8888)) {
            ConsoleUI consoleUI = new ConsoleUI(connector);
            consoleUI.scanRequests();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
