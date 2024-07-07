package client;

import manager.Manager;
import validator.ConsoleValidator;
import validator.ValidationResult;

import static messages.UserMessages.ENTER_COMMAND;
import static messages.UserMessages.NO_SUCH_COMMAND;

public class ConsoleUI {
    private final Manager manager;
    private final ConsoleReader consoleReader;
    private String command;
    private String arg;

    public ConsoleUI(Manager manager) {
        this.manager = manager;
        this.consoleReader = new ConsoleReader();
        this.command = "";
        this.arg = "";
    }

    public void start() {
        String request;
        while (true) {
            showMessage(ENTER_COMMAND);
            request = consoleReader.readRequest();
            fillCommand(request);
            if (arg == null) {
                switch (command) {
                    case "help" -> {
                        showMessage(manager.help());
                    }
                    case "info" -> {
                        showMessage(manager.info());
                    }
                    case "show" -> {
                        showMessage(manager.show());
                    }
                    case "exit" -> {
                        showMessage(manager.exit());
                        return;
                    }
                    case "add" -> {
                        showMessage(manager.add(consoleReader.createBand()));
                    }
                    case "clear" -> {
                        showMessage(manager.clear());
                    }
                    case "history" -> {
                        showMessage(manager.history());
                    }
                    case "add_if_min" -> {
                        showMessage(manager.addIfMin(consoleReader.createBand()));
                    }
                    case "remove_lower" -> {
                        showMessage(manager.removeLower(consoleReader.createBand()));
                    }
                    case "min_by_best_album" -> {
                        showMessage(manager.minByBestAlbum());
                    }
                    case "filter_by_best_album" -> {
                        showMessage(manager.filterByBestAlbum(consoleReader.readBestAlbum()));
                    }
                    case "print_field_asc_best_album" -> {
                        showMessage(manager.printFieldAscBestAlbum());
                    }
                    default -> {
                        showMessage(NO_SUCH_COMMAND);
                    }
                }
            } else {
                ValidationResult validationResult = new ConsoleValidator().isCorrectArg(arg);
                if (validationResult.isValid()) {
                    switch (command) {
                        case "update" -> {
                            showMessage(manager.updateById(consoleReader.createBand(), Integer.parseInt(arg)));
                        }
                        case "remove" -> {
                            showMessage(manager.removeById(Integer.parseInt(arg)));
                        }
                        default -> {
                            showMessage(NO_SUCH_COMMAND);
                        }
                    }
                } else {
                    showMessage(validationResult.getErrorMessage());
                }
            }
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
