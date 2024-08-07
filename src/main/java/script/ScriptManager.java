package script;

import command.Command;
import command.CommandSerializer;
import exceptions.EmptyFileException;
import exceptions.MusicBandParsingException;
import music.BestAlbum;
import music.MusicBand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static messages.ResultMessages.*;

public class ScriptManager {
    private final MusicBandFieldsParser parser;
    private final CommandSerializer serializer;

    public ScriptManager() {
        this.parser = new MusicBandFieldsParser();
        this.serializer = new CommandSerializer();
    }

    public ScriptResult getScriptResult(String path) {
        try {
            return executeScript(path);
        } catch (FileNotFoundException e) {
            ScriptResult scriptResult = new ScriptResult();
            scriptResult.addErrorMessages(NO_SUCH_FILE);
            return scriptResult;
        } catch (IOException e) {
            ScriptResult scriptResult = new ScriptResult();
            scriptResult.addErrorMessages(FILE_READER_MISTAKE);
            return scriptResult;
        } catch (SecurityException | EmptyFileException e) {
            ScriptResult scriptResult = new ScriptResult();
            scriptResult.addErrorMessages(e.getMessage());
            return scriptResult;
        }
    }

    public ScriptResult executeScript(String path) throws IOException, SecurityException, EmptyFileException {
        ScriptResult scriptResult = new ScriptResult();
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(NO_SUCH_FILE);
        }
        if (file.canRead()) {
            String commandName = null;
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                try {
                    MusicBand musicBand;
                    String id;
                    String command = fileScanner.nextLine().toLowerCase().trim();
                    if (command.startsWith("update")) {
                        commandName = "update";
                        musicBand = parser.createBand(fileScanner);
                        id = command.split(" ", 2)[1];
                        parser.parseId(id);
                        scriptResult.addCommand(new Command(commandName, serializer.serializeMusicBand(musicBand), id));
                    } else if (command.startsWith("remove")) {
                        commandName = "remove";
                        id = command.split(" ", 2)[1];
                        parser.parseId(id);
                        scriptResult.addCommand(new Command(commandName, id));
                    } else if (command.startsWith("remove_lower")) {
                        commandName = "remove_lower";
                        String sales = command.split(" ", 2)[1];
                        parser.parseSales(sales);
                        scriptResult.addCommand(new Command(commandName, sales));
                    } else {
                        switch (command) {
                            case "help", "info", "show", "clear", "history", "min_by_best_album",
                                 "print_field_asc_best_album" -> {
                                commandName = command;
                                scriptResult.addCommand(new Command(commandName));
                            }
                            case "add", "add_if_min" -> {
                                commandName = command;
                                musicBand = parser.createBand(fileScanner);
                                scriptResult.addCommand(new Command(commandName, serializer.serializeMusicBand(musicBand)));
                            }
                            case "filter_by_best_album" -> {
                                commandName = command;
                                BestAlbum bestAlbum = parser.parseBestAlbum(fileScanner);
                                scriptResult.addCommand(new Command(commandName, serializer.serializeBestAlbum(bestAlbum)));
                            }
                        }
                    }
                } catch (MusicBandParsingException e) {
                    scriptResult.addErrorMessages("Command: " + commandName + " " + "Error message: " + e.getMessage());
                }
            }
        } else {
            throw new SecurityException(NOT_RIGHT_ACCESS_ON_READ);
        }
        return scriptResult;
    }
}
