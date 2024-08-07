import client.ConsoleUI;

import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleUI consoleUI = new ConsoleUI(InetAddress.getByName("localhost"), 8888);
        consoleUI.start();
    }
}
