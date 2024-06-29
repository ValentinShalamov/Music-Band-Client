package manager;

import music.BestAlbum;
import music.MusicBand;

import static manager.MessagesByResults.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Manager {
    private HashSet<MusicBand> musicBands;
    private final LocalDateTime localDateTime;
    private String response;
    private final String[] history;

    public Manager(HashSet<MusicBand> musicBands) {
        this.musicBands = musicBands;
        this.localDateTime = LocalDateTime.now();
        this.history = new String[12];
    }

    private void shiftRight(String[] strings) {
        for (int i = strings.length - 1; i > 0; i--) {
            strings[i] = strings[i-1];
        }
    }

    private void addHistory(String commandName) {
        shiftRight(history);
        history[0] = commandName;
    }

    public String getResponse() {
        return response;
    }

    public void help() {
        addHistory(" - help \n");
        response = LIST_OF_AVAILABLE_COMMAND;
    }

    public void info() {
        addHistory(" - info \n");
        String typeCollection = String.format("Type of collection is %s \n", musicBands.getClass().getTypeName());
        String countElements = String.format("Count of elements is %d \n", musicBands.size());
        String dateInit = String.format("Time init is %s \n", localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        response = typeCollection + countElements + dateInit;
    }

    public void exit() {
        response = EXIT_MESSAGE;
    }

    public void show() {
        addHistory(" - show \n");
        if (musicBands.isEmpty()) {
            response = COLLECTION_IS_EMPTY;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            musicBands.forEach((musicBand) -> stringBuilder.append(musicBand.toString()));
            response = stringBuilder.toString();
        }
    }

    public void add(MusicBand musicBand) {
        addHistory(" - add \n");
        if (musicBands.add(musicBand)) {
            response = MUSIC_BAND_HAS_BEEN_ADDED_SUCCESSFUL;
        } else {
            MusicBand.decId();
            response = ADDITION_MISTAKE;
        }
    }

    private boolean hasId(long id) {
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getId() == id) return true;
        }
        return false;
    }

    public void updateById(MusicBand musicBand, long id) {
        addHistory(" - update <id> \n");
        MusicBand.decId();
        if (!hasId(id)) {
            response = NOT_SUCH_ID;
            return;
        }
        for (MusicBand band : musicBands) {
            if (band.getId() == id) {
                musicBands.remove(band);
                musicBand.setId(id);
                if (musicBands.add(musicBand)) {
                    response = MUSIC_BAND_HAS_BEEN_UPDATED_SUCCESSFUL;
                } else {
                    response = UPDATED_MISTAKE;
                }
                return;
            }
        }
    }

    public void removeById(int id) {
        addHistory(" - remove <id> \n");
        if (!hasId(id)) {
            response = NOT_SUCH_ID;
            return;
        }
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getId() == id) {
                if (musicBands.remove(musicBand)) {
                    MusicBand.decId();
                    response = MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL;
                } else {
                    response = DELETED_MISTAKE;
                }
                return;
            }
        }
    }

    public void clear() {
        addHistory(" - clear \n");
        musicBands.clear();
        MusicBand.clearGlobId();
        response = COLLECTION_HAS_BEEN_DELETED;
    }

    public void history() {
        StringBuilder builder = new StringBuilder();
        builder.append("last");
        for (String s : history) {
            if (s != null) {
                builder.append(s);
            }
        }
        response = builder.toString();
    }

    public void addIfMin(MusicBand musicBand) {
        addHistory(" - add_if_min \n");
        if (musicBands.isEmpty()) {
            MusicBand.decId();
            response = CANNOT_ADD_ELEM_IN_EMPTY_COLLECTION;
            return;
        }
        if (musicBand.getBestAlbum().getSales() < Collections.min(musicBands).getBestAlbum().getSales()) {
            if (musicBands.add(musicBand)) {
                response = MUSIC_BAND_HAS_BEEN_ADDED_SUCCESSFUL;
            } else {
                response = ADDITION_MISTAKE;
            }
        } else {
            MusicBand.decId();
            response = SALES_BIGGER_THAN_MIN_ELEMENT;
        }
    }

    public void removeLower(MusicBand musicBand) {
        addHistory("- remove_lower \n");
        if (musicBands.isEmpty()) {
            MusicBand.decId();
            response = COLLECTION_IS_EMPTY;
            return;
        }
        long id;
        StringBuilder builder = new StringBuilder();
        HashSet<MusicBand> copyMusicBands = new HashSet<>(musicBands);

        for (MusicBand band : copyMusicBands) {
            if (band.getBestAlbum().getSales() < musicBand.getBestAlbum().getSales()) {
                id = band.getId();
                if (musicBands.remove(band)) {
                    builder.append(MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL_ID).append(id).append('\n');
                } else {
                    builder.append(DELETED_MISTAKE_ID).append(id).append('\n');
                }
            }
        }
        MusicBand.decId();
        response = builder.toString();
        if (response.isEmpty()) {
            response = ALL_ELEMENT_BIGGER;
        }
    }

    public void minByBestAlbum() {
        addHistory(" - min_by_best_album \n");
        if (musicBands.isEmpty()) {
            response = COLLECTION_IS_EMPTY;
            return;
        }
        response = Collections.min(musicBands).toString();
    }

    public void filterByBestAlbum(BestAlbum bestAlbum) {
        addHistory("- filter_by_best_album \n");
        if (musicBands.isEmpty()) {
            response = COLLECTION_IS_EMPTY;
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getBestAlbum().getSales() == bestAlbum.getSales()) {
                builder.append(musicBand);
            }
        }
        response = builder.toString();
        if (response.isEmpty()) {
            response = NOT_SUCH_ELEMENTS;
        }
    }

    public void printFieldAscBestAlbum() {
        addHistory(" - print_field_asc_best_album \n");
        if (musicBands.isEmpty()) {
            response = COLLECTION_IS_EMPTY;
            return;
        }
        List<MusicBand> sortBands = new ArrayList<>(musicBands);
        Collections.sort(sortBands);
        StringBuilder builder = new StringBuilder();
        for (MusicBand musicBand : sortBands) {
            builder.append("id = ").append(musicBand.getId()).append(", sales = ").append(musicBand.getBestAlbum().getSales()).append('\n');
        }
        response = builder.toString();
    }
}
