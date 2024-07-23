package manager;

import music.BestAlbum;
import music.MusicBand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static messages.ResultMessages.*;

public class CollectionManager {
    private final Set<MusicBand> musicBands;
    private final LocalDateTime localDateTime;
    private final ArrayDeque<String> history;

    public CollectionManager(Set<MusicBand> musicBands) {
        this.musicBands = musicBands;
        this.localDateTime = LocalDateTime.now();
        this.history = new ArrayDeque<>();
    }

    private void addHistory(String commandName) {
        history.addFirst(commandName);
        if (history.size() > 12) {
            history.removeLast();
        }
    }

    public String help() {
        addHistory(" - help \n");
        return LIST_OF_AVAILABLE_COMMAND;
    }

    public String info() {
        addHistory(" - info \n");
        String typeCollection = String.format("Type of collection is %s \n", musicBands.getClass().getTypeName());
        String countElements = String.format("Count of elements is %d \n", musicBands.size());
        String dateInit = String.format("Time init is %s \n", localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return typeCollection + countElements + dateInit;
    }

    public String show() {
        addHistory(" - show \n");
        if (musicBands.isEmpty()) {
            return COLLECTION_IS_EMPTY;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            musicBands.forEach((musicBand) -> stringBuilder.append(musicBand.toString()));
            return stringBuilder.toString();
        }
    }

    public String add(MusicBand musicBand) {
        addHistory(" - add \n");
        if (musicBands.add(musicBand)) {
            return MUSIC_BAND_HAS_BEEN_ADDED_SUCCESSFUL;
        } else {
            MusicBand.decId();
            return ADDITION_MISTAKE;
        }
    }

    public String updateById(MusicBand musicBand, long id) {
        addHistory(" - update <id> \n");
        MusicBand.decId();
        for (MusicBand band : musicBands) {
            if (band.getId() == id) {
                musicBand.setId(id);
                if (musicBands.add(musicBand)) {
                    musicBands.remove(band);
                    return MUSIC_BAND_HAS_BEEN_UPDATED_SUCCESSFUL;
                } else {
                    return UPDATED_MISTAKE;
                }
            }
        }
        return NO_SUCH_ID;
    }

    public String removeById(long id) {
        addHistory(" - remove <id> \n");
        if (musicBands.removeIf((band) -> band.getId() == id)) {
            MusicBand.decId();
            return MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL;
        } else {
            return DELETED_MISTAKE;
        }
    }

    public String clear() {
        addHistory(" - clear \n");
        musicBands.clear();
        MusicBand.clearGlobId();
        return COLLECTION_HAS_BEEN_DELETED;
    }

    public String history() {
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (String s : history) {
            if (count == 0 && s != null) {
                builder.append(s).delete(s.length() - 2, s.length()).append(" - last command \n");
                count++;
                continue;
            }
            if (s != null) {
                builder.append(s);
            }
        }
        return builder.toString();
    }

    public String addIfMin(MusicBand musicBand) {
        addHistory(" - add_if_min \n");
        if (musicBands.isEmpty()) {
            MusicBand.decId();
            return CANNOT_ADD_ELEM_IN_EMPTY_COLLECTION;
        }
        if (musicBand.getBestAlbum().getSales() < Collections.min(musicBands).getBestAlbum().getSales()) {
            if (musicBands.add(musicBand)) {
                return MUSIC_BAND_HAS_BEEN_ADDED_SUCCESSFUL;
            } else {
                return ADDITION_MISTAKE;
            }
        } else {
            MusicBand.decId();
            return SALES_BIGGER_THAN_MIN_ELEMENT;
        }
    }

    public String removeLower(long sales) {
        addHistory("- remove_lower \n");
        if (musicBands.isEmpty()) {
            return COLLECTION_IS_EMPTY;
        }
        long id;
        StringBuilder builder = new StringBuilder();
        HashSet<MusicBand> copyMusicBands = new HashSet<>(musicBands);
        for (MusicBand band : copyMusicBands) {
            if (band.getBestAlbum().getSales() < sales) {
                id = band.getId();
                if (musicBands.remove(band)) {
                    builder.append(MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL_ID).append(id).append('\n');
                } else {
                    builder.append(DELETED_MISTAKE_ID).append(id).append('\n');
                }
            }
        }
        MusicBand.decId();
        if (builder.toString().isEmpty()) {
            return ALL_ELEMENT_BIGGER;
        }
        return builder.toString();
    }

    public String minByBestAlbum() {
        addHistory(" - min_by_best_album \n");
        if (musicBands.isEmpty()) {
            return COLLECTION_IS_EMPTY;
        }
        return Collections.min(musicBands).toString();
    }

    public String filterByBestAlbum(BestAlbum bestAlbum) {
        addHistory("- filter_by_best_album \n");
        if (musicBands.isEmpty()) {
            return COLLECTION_IS_EMPTY;
        }
        StringBuilder builder = new StringBuilder();
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getBestAlbum().getSales() == bestAlbum.getSales()) {
                builder.append(musicBand);
            }
        }
        if (builder.toString().isEmpty()) {
            return NOT_SUCH_ELEMENTS;
        }
        return null;
    }

    public String printFieldAscBestAlbum() {
        addHistory(" - print_field_asc_best_album \n");
        if (musicBands.isEmpty()) {
            return COLLECTION_IS_EMPTY;
        }
        List<MusicBand> sortBands = new ArrayList<>(musicBands);
        Collections.sort(sortBands);
        StringBuilder builder = new StringBuilder();
        for (MusicBand musicBand : sortBands) {
            builder.append("id = ").append(musicBand.getId()).append(", sales = ").append(musicBand.getBestAlbum().getSales()).append('\n');
        }
        return builder.toString();
    }

    public String readMusicBand(Set<MusicBand> anotherBand) {
        musicBands.clear();
        musicBands.addAll(anotherBand);
        MusicBand.setGlobId(anotherBand.stream()
                .map(MusicBand::getId)
                .max(Long::compare)
                .orElse(0L));
        return COLLECTION_HAS_BEEN_READ;
    }

    public Set<MusicBand> getMusicBands() {
        return Collections.unmodifiableSet(musicBands);
    }

}
