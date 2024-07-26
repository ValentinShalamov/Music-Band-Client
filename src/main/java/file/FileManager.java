package file;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import exceptions.EmptyFileException;
import exceptions.MusicBandParsingException;
import manager.CollectionManager;
import music.MusicBand;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static messages.ResultMessages.*;

public class FileManager {
    private final CollectionManager collectionManager;
    private final MusicBandFieldsParser parser;

    public FileManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.parser = new MusicBandFieldsParser();
    }

    public String save(String path) throws IOException, SecurityException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.canWrite()) {
            JsonElement jsonElement = JsonParser.parseString(gson.toJson(collectionManager.getMusicBands()));
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
                gson.toJson(jsonElement, writer);
                return SAVING_SUCCESSFUL + file.getAbsolutePath();
            }
        } else {
            throw new SecurityException(NOT_RIGHT_ACCESS_ON_WRITE);
        }
    }

    public HashSet<MusicBand> read(String path) throws JsonSyntaxException, SecurityException, IOException, EmptyFileException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(NO_SUCH_FILE);
        }
        if (file.canRead()) {
            try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
                Type musicBandsList = new TypeToken<HashSet<MusicBand>>() {
                }.getType();
                HashSet<MusicBand> bands = gson.fromJson(reader, musicBandsList);
                if (bands != null) {
                    return bands;
                } else {
                    throw new EmptyFileException(FILE_IS_EMPTY);
                }
            }
        } else {
            throw new SecurityException(NOT_RIGHT_ACCESS_ON_READ);
        }
    }

    public String executeScript(String path) throws IOException, SecurityException, EmptyFileException {
        File file = new File(path);
        ArrayList<String> messages = new ArrayList<>();
        if (!file.exists()) {
            throw new FileNotFoundException(NO_SUCH_FILE);
        }
        if (file.canRead()) {
            Scanner fileScanner = new Scanner(file);
            try {
                while (fileScanner.hasNext()) {
                    String command = fileScanner.nextLine().toLowerCase().trim();
                    if (command.startsWith("update")) {
                        messages.add(collectionManager.updateById(parser.createBand(fileScanner),
                                parser.parseId(command.split(" ", 2)[1])));
                    } else if (command.startsWith("remove")) {
                        messages.add(collectionManager.removeById(parser.parseId(command.split(" ", 2)[1])));
                    } else if (command.startsWith("remove_lower")) {
                        messages.add(collectionManager.removeLower(parser.parseSales(command.split(" ", 2)[1])));
                    } else {
                        switch (command) {
                            case "help" -> messages.add(collectionManager.help());
                            case "info" -> messages.add(collectionManager.info());
                            case "show" -> messages.add(collectionManager.show());
                            case "add" -> {
                                MusicBand musicBand = parser.createBand(fileScanner);
                                messages.add(collectionManager.add(musicBand));
                            }
                            case "clear" -> messages.add(collectionManager.clear());
                            case "history" -> messages.add(collectionManager.history());
                            case "add_if_min" -> {
                                MusicBand musicBand = parser.createBand(fileScanner);
                                messages.add(collectionManager.addIfMin(musicBand));
                            }
                            case "min_by_best_album" -> messages.add(collectionManager.minByBestAlbum());
                            case "filter_by_best_album" -> messages.add(collectionManager.filterByBestAlbum(parser.parseBestAlbum(fileScanner)));
                            case "print_field_asc_best_album" -> messages.add(collectionManager.printFieldAscBestAlbum());
                            case "save" -> messages.add(save(parser.parsePath(fileScanner.nextLine())));
                            case "execute_script" -> messages.add(executeScript(parser.parsePath(fileScanner.nextLine())));
                        }
                    }
                }
            } catch (MusicBandParsingException e) {
                messages.add(e.getMessage());
            }
        } else {
            throw new SecurityException(NOT_RIGHT_ACCESS_ON_READ);
        }
        if (messages.isEmpty()) {
            throw new EmptyFileException(FILE_IS_EMPTY);
        }
        String allMessages = messages.toString();
        return allMessages.substring(1, allMessages.length() - 1);
    }
}
