package music;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MusicBand implements Comparable<MusicBand> {
    private Long id;
    private String name;
    private final java.time.LocalDateTime creationDate;
    private int numberOfParticipants;
    private final java.time.LocalDate establishmentDate;
    private MusicGenre genre;
    private BestAlbum bestAlbum;

    private static long globId;

    public MusicBand() {
        this.id = ++globId;
        this.creationDate = LocalDateTime.now();
        this.establishmentDate = creationDate.toLocalDate();
    }

    @Override
    public int compareTo(MusicBand anotherBand) {
        return Long.compare(bestAlbum.getSales(), anotherBand.bestAlbum.getSales());
    }

    public Long getId() {
        return id;
    }

    public BestAlbum getBestAlbum() {
        return bestAlbum;
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

    public void setId(long id) {
        this.id = id;
    }

    public static void setGlobId(long id) {
        globId = id;
    }

    public static void clearGlobId() {
        globId = 0;
    }

    public static void decId() {
        globId -= 1;
    }

    @Override
    public String toString() {
        return String.format("id = %d, name is %s, time creation is %s, " +
                "number of participants = %d, establishment date is %s, " +
                "genre is %s, the best album is %s, count sales of the best album = %d \n",
                id, name, creationDate.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")), numberOfParticipants, establishmentDate, genre,
                bestAlbum.getName(), bestAlbum.getSales());
    }
}
