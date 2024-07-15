
import client.ConsoleUI;
import manager.CollectionManager;
import file.FileManager;
import manager.Manager;

import music.MusicBand;


import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<MusicBand> musicBands = new HashSet<>();
        CollectionManager collectionManager = new CollectionManager(musicBands);
        FileManager fileManager = new FileManager(collectionManager);
        Manager manager = new Manager(collectionManager, fileManager);
        ConsoleUI consoleUI = new ConsoleUI(manager);
        consoleUI.start();
    }
}
