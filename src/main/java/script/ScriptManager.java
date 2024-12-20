package script;

import command.Command;
import command.CommandSerializer;
import exceptions.EmptyFileException;
import exceptions.MusicBandParsingException;
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
            scriptResult.addErrorMessages(READ_ERROR);
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
            String command = null;
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                try {
                    MusicBand musicBand;
                    String id;
                    command = fileScanner.nextLine().toLowerCase().trim();
                    if (command.startsWith("update")) {
                        musicBand = parser.createBand(fileScanner);
                        id = command.split(" ", 2)[1];
                        parser.parseId(id);
                        scriptResult.addCommand(new Command("update", serializer.serializeMusicBand(musicBand), id));
                    } else if (command.startsWith("remove_lower")) {
                        String sales = command.split(" ", 2)[1];
                        parser.parseSales(sales);
                        scriptResult.addCommand(new Command("remove_lower", sales));
                    } else if (command.startsWith("remove")) {
                        id = command.split(" ", 2)[1];
                        parser.parseId(id);
                        scriptResult.addCommand(new Command("remove", id));
                    } else if (command.startsWith("filter")) {
                        String sales = command.split(" ",2)[1];
                        parser.parseSales(sales);
                        scriptResult.addCommand(new Command("filter", sales));
                    } else {
                        switch (command) {
                            case "help", "info", "show", "show_mine", "clear", "history", "history_clear", "show_min",
                                 "print_asc", "print_desc", "exit" -> scriptResult.addCommand(new Command(command));

                            case "add", "add_if_min" -> {
                                musicBand = parser.createBand(fileScanner);
                                scriptResult.addCommand(new Command(command, serializer.serializeMusicBand(musicBand)));
                            }
                            default -> {
                                if (!command.isEmpty()) {
                                    scriptResult.addErrorMessages("Command: " + command + " " + "is incorrect command");
                                }
                            }
                        }
                    }
                } catch (MusicBandParsingException e) {
                    scriptResult.addErrorMessages("Command: " + command + " " + "Error message: " + e.getMessage());
                }
            }
        } else {
            throw new SecurityException(NO_READ_PERMISSION);
        }
        return scriptResult;
    }
}
