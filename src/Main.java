import client.ConsoleUI;
import manager.Manager;
import music.MusicBand;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<MusicBand> musicBands = new HashSet<>();
        Manager manager = new Manager(musicBands);
        ConsoleUI consoleUI = new ConsoleUI(manager);
        consoleUI.start();

    }
}