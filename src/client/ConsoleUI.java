package client;

import manager.Manager;
import validator.ValidationResult;
import validator.Validator;

import java.util.Scanner;

import static client.MessagesForUser.*;

public class ConsoleUI {
    private final Scanner console;
    private final Manager manager;
    private final Reader reader;
    private final Validator validator;
    private String command;
    private String arg;

    public ConsoleUI(Manager manager) {
        this.manager = manager;
        this.console = new Scanner(System.in);
        this.reader = new Reader(console);
        this.command = "";
        this.arg = "";
        this.validator = new Validator();
    }

    public void start() {
        String request;
        while (true) {
            sleep();
            showMessage(ENTER_COMMAND);
            request = console.nextLine().toLowerCase().trim();
            fillCommand(request);

            if (!hasArgument()) {
                switch (command) {
                    case "help" -> {
                        manager.help();
                        showMessage(manager.getResponse());
                    }
                    case "info" -> {
                        manager.info();
                        showMessage(manager.getResponse());
                    }
                    case "show" -> {
                        manager.show();
                        showMessage(manager.getResponse());
                    }
                    case "exit" -> {
                        manager.exit();
                        showMessage(manager.getResponse());
                        return;
                    }
                    case "add" -> {
                        manager.add(reader.createBand());
                        showMessage(manager.getResponse());
                    }
                    case "clear" -> {
                        manager.clear();
                        showMessage(manager.getResponse());
                    }
                    case "history" -> {
                        manager.history();
                        showMessage(manager.getResponse());
                    }
                    case "add_if_min" -> {
                        manager.addIfMin(reader.createBand());
                        showMessage(manager.getResponse());
                    }
                    case "remove_lower" -> {
                        manager.removeLower(reader.createBand());
                        showMessage(manager.getResponse());
                    }
                    case "min_by_best_album" -> {
                        manager.minByBestAlbum();
                        showMessage(manager.getResponse());
                    }
                    case "filter_by_best_album" -> {
                        manager.filterByBestAlbum(reader.readBestAlbum());
                        showMessage(manager.getResponse());
                    }
                    case "print_field_asc_best_album" -> {
                        manager.printFieldAscBestAlbum();
                        showMessage(manager.getResponse());
                    }
                    default -> {
                        showMessage(NOT_SUCH_COMMAND);
                    }
                }
            } else {
                if (isCorrectArg()) {
                    switch (command) {
                        case "update" -> {
                            manager.updateById(reader.createBand(), Integer.parseInt(arg));
                            showMessage(manager.getResponse());
                        }
                        case "remove" -> {
                            manager.removeById(Integer.parseInt(arg));
                            showMessage(manager.getResponse());
                        }
                        default -> {
                            showMessage(NOT_SUCH_COMMAND);
                        }
                    }
                } else {
                    showMessage(INCORRECT_ARG); // возможно добавить доб сообщения от валидатора
                }
            }
            sleep();

        }
    }

    private boolean isCorrectArg() {
        validator.setStringInputData(arg);
        ValidationResult validationResult = validator.isCorrectArg();
        return validationResult.isCorrectData();
    }

    private boolean hasArgument() {
        return arg != null;
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

    private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException \n");
        }
    }

}
