package manager;

import music.BestAlbum;
import music.MusicBand;

public class Manager {
    private final CollectionManager collectionManager;

    public Manager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String help() {
        return collectionManager.help();
    }

    public String info() {
        return collectionManager.info();
    }

    public String exit() {
        return collectionManager.exit();
    }

    public String show() {
        return collectionManager.show();
    }

    public String add(MusicBand musicBand) {
        return collectionManager.add(musicBand);
    }

    public String updateById(MusicBand musicBand, long id) {
        return collectionManager.updateById(musicBand, id);
    }

    public String removeById(int id) {
        return collectionManager.removeById(id);
    }

    public String clear() {
        return collectionManager.clear();
    }

    public String history() {
        return collectionManager.history();
    }

    public String addIfMin(MusicBand musicBand) {
        return collectionManager.addIfMin(musicBand);
    }

    public String removeLower(MusicBand musicBand) {
        return collectionManager.removeLower(musicBand);
    }

    public String minByBestAlbum() {
        return collectionManager.minByBestAlbum();
    }

    public String filterByBestAlbum(BestAlbum bestAlbum) {
        return collectionManager.filterByBestAlbum(bestAlbum);
    }

    public String printFieldAscBestAlbum() {
        return collectionManager.printFieldAscBestAlbum();
    }
}
