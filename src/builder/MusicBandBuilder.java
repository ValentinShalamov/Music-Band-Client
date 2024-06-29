package builder;

import music.BestAlbum;
import music.MusicBand;
import music.MusicGenre;

public class MusicBandBuilder {
    private final MusicBand musicBand = new MusicBand();

    public MusicBandBuilder setName(String name) {
        musicBand.setName(name);
        return this;
    }

    public MusicBandBuilder setNumberOfParticipants(int numberOfParticipants) {
        musicBand.setNumberOfParticipants(numberOfParticipants);
        return this;
    }

    public MusicBandBuilder setGenre(MusicGenre genre) {
        musicBand.setGenre(genre);
        return this;
    }

    public MusicBandBuilder setBestAlbum(BestAlbum bestAlbum) {
        musicBand.setBestAlbum(bestAlbum);
        return this;
    }

    public MusicBand build() {
        return musicBand;
    }
}
