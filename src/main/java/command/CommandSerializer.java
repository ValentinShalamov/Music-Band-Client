package command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import music.MusicBand;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommandSerializer {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public String serializeCommand(Command command) {
        return gson.toJson(command);
    }

    public String serializeMusicBand(MusicBand musicBand) {
        return gson.toJson(musicBand);
    }

}
