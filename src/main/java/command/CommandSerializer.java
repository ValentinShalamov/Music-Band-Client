package command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import music.BestAlbum;
import music.MusicBand;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommandSerializer {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public String parseString(Command command) {
        JsonElement jsonElement = JsonParser.parseString(gson.toJson(command));
        return gson.toJson(jsonElement);
    }

    public String serializeMusicBand(MusicBand musicBand) {
        JsonElement jsonElement = JsonParser.parseString(gson.toJson(musicBand));
        return gson.toJson(jsonElement);
    }

    public String serializeBestAlbum(BestAlbum bestAlbum) {
        JsonElement jsonElement = JsonParser.parseString(gson.toJson(bestAlbum));
        return gson.toJson(jsonElement);
    }

}
