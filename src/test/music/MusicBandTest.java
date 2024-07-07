package test.music;

import music.MusicBand;
import org.junit.jupiter.api.Test;

public class MusicBandTest {
    MusicBand musicBand;

    @Test
    void shouldAutoId() {
        musicBand = new MusicBand();
    }
}
