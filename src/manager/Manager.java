package manager;

import com.google.gson.JsonSyntaxException;
import exceptions.EmptyFileException;
import file.FileManager;
import music.BestAlbum;
import music.MusicBand;

import java.io.FileNotFoundException;
import java.io.IOException;
import static messages.ResultMessages.*;

public class Manager {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public Manager(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    public String help() {
        return collectionManager.help();
    }

    public String info() {
        return collectionManager.info();
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

    public String removeLower(long sales) {
        return collectionManager.removeLower(sales);
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

    public String save(String path) {
        try {
            return fileManager.save(path);
        } catch (IOException ex) {
            return SAVING_MISTAKE;
        } catch (SecurityException e) {
            return e.getMessage();
        }
    }

    public String read(String fileName) {
        try {
            return collectionManager.readMusicBand(fileManager.read(fileName));
        } catch (FileNotFoundException | SecurityException | EmptyFileException e) {
            return e.getMessage();
        } catch (JsonSyntaxException e) {
            return FILE_CONTENT_INCORRECT;
        } catch (IOException e) {
            return FILE_READER_MISTAKE;
        }
    }

    public String executeScript(String path) {
        try {
            return fileManager.executeScript(path);
        } catch (FileNotFoundException e) {
            return NO_SUCH_FILE;
        } catch (IOException e) {
            return FILE_READER_MISTAKE;
        } catch (SecurityException | EmptyFileException e) {
            return e.getMessage();
        }
    }
}
