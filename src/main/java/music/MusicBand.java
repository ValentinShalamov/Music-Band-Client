package music;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MusicBand implements Comparable<MusicBand> {
    private Long id;
    private String name;
    private final java.time.LocalDateTime creationDate;
    private int numberOfParticipants;
    private final java.time.LocalDate establishmentDate;
    private MusicGenre genre;
    private BestAlbum bestAlbum;

    public MusicBand() {
        this.creationDate = LocalDateTime.now();
        this.establishmentDate = creationDate.toLocalDate();
    }

    @Override
    public int compareTo(MusicBand anotherBand) {
        return Long.compare(bestAlbum.sales(), anotherBand.bestAlbum.sales());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setBestAlbum(BestAlbum bestAlbum) {
        this.bestAlbum = bestAlbum;
    }

    @Override
    public String toString() {
        return String.format("id = %d, name is %s, time creation is %s, " +
                        "number of participants = %d, establishment date is %s, " +
                        "genre is %s, the best album is %s, count sales of the best album = %d \n",
                id, name, creationDate.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")), numberOfParticipants, establishmentDate, genre,
                bestAlbum.name(), bestAlbum.sales());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return Objects.equals(name, musicBand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
