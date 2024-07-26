package client;

import exceptions.UserCancelledOperationException;
import manager.Manager;
import validator.ConsoleValidator;
import validator.ValidationResult;

import static messages.ResultMessages.PROGRAM_HAS_BEEN_COMPLETED;
import static messages.UserMessages.*;

public class ConsoleUI {
    private final Manager manager;
    private final ConsoleReader consoleReader;
    private String command;
    private String arg;
    private final String AUTOSAVE_PATH = "AUTOSAVE.json";

    public ConsoleUI(Manager manager) {
        this.manager = manager;
        this.consoleReader = new ConsoleReader();
        this.command = "";
        this.arg = "";
    }

    public void start() {
        initMusicBands();
        String request;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            showMessage(PROGRAM_HAS_BEEN_COMPLETED);
            showMessage(manager.save(AUTOSAVE_PATH));
        }));
        while (true) {
            try {
                showMessage(ENTER_COMMAND);
                request = consoleReader.readRequest();
                fillCommand(request.toLowerCase());
                if (arg == null) {
                    switch (command) {
                        case "help" -> showMessage(manager.help());
                        case "info" -> showMessage(manager.info());
                        case "show" -> showMessage(manager.show());
                        case "exit" -> {
                            return;
                        }
                        case "add" -> showMessage(manager.add(consoleReader.createBand()));
                        case "clear" -> showMessage(manager.clear());
                        case "history" -> showMessage(manager.history());
                        case "add_if_min" -> showMessage(manager.addIfMin(consoleReader.createBand()));
                        case "min_by_best_album" -> showMessage(manager.minByBestAlbum());
                        case "filter_by_best_album" -> showMessage(manager.filterByBestAlbum(consoleReader.readBestAlbum()));
                        case "print_field_asc_best_album" -> showMessage(manager.printFieldAscBestAlbum());
                        case "save" -> showMessage(manager.save(consoleReader.readPath()));
                        case "execute_script" -> showMessage(manager.executeScript(consoleReader.readPath()));
                        default -> showMessage(NO_SUCH_COMMAND);
                    }
                } else {
                    ValidationResult validationResult = new ConsoleValidator().isCorrectArg(arg);
                    if (validationResult.isValid()) {
                        switch (command) {
                            case "update" -> showMessage(manager.updateById(consoleReader.createBand(), Integer.parseInt(arg)));
                            case "remove" -> showMessage(manager.removeById(Integer.parseInt(arg)));
                            case "remove_lower" -> showMessage(manager.removeLower(Long.parseLong(arg)));
                            default -> showMessage(NO_SUCH_COMMAND);
                        }
                    } else {
                        showMessage(validationResult.getErrorMessage());
                    }
                }
            } catch (UserCancelledOperationException e) {
                showMessage(e.getMessage());
            }
        }
    }

    private void initMusicBands() {
        System.out.println(GREET_MESSAGE);
        String env = System.getenv("PATH_TO_FILE_COLLECTION");
        if (env != null) {
            showMessage(manager.read(env));
        } else {
            showMessage(WORK_WITH_EMPTY_COLLECTION);
        }
    }

    private void fillCommand(String request) {
        String[] commandWithArg = request.split(" ", 2);
        if (commandWithArg.length == 1) {
            command = commandWithArg[0];
            arg = null;
        } else {
            command = commandWithArg[0];
            arg = commandWithArg[1];
        }
    }

    private void showMessage(String message) {
        System.out.println(message);
    }

}
