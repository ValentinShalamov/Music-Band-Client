import client.ConsoleUI;
import server.ServerConnector;

import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try(ServerConnector connector = new ServerConnector(new Socket())) {
            ConsoleUI consoleUI = new ConsoleUI(connector);
            consoleUI.scanRequests();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
