package client;

import command.Command;
import command.CommandSerializer;
import exceptions.UserCancelledOperationException;
import script.ScriptManager;
import script.ScriptResult;
import server.ServerConnector;
import validator.ConsoleValidator;
import validator.ValidationResult;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

import static messages.ConnectionMessages.SUCCESSFUL_CONNECT;
import static messages.ConnectionMessages.WAITING_CONNECTION;
import static messages.ResultMessages.AUTHORIZATION_SUCCESSFUL;
import static messages.UserMessages.ENTER_COMMAND;

public class ConsoleUI {
    private final ConsoleReader consoleReader;
    private String commandName;
    private String arg;
    private final CommandSerializer serializer;
    private final ScriptManager scriptManager;
    private final ServerConnector connector;
    private boolean isAuthenticated = false;

    public ConsoleUI(ServerConnector connector) {
        this.consoleReader = new ConsoleReader();
        this.commandName = "";
        this.arg = "";
        this.serializer = new CommandSerializer();
        this.scriptManager = new ScriptManager();
        this.connector = connector;
    }


    public boolean hasConnect() throws IOException {
        showMessage(WAITING_CONNECTION);
        String response = connector.connect();
        showMessage(response);
        if (response.startsWith(SUCCESSFUL_CONNECT)) {
            showMessage(connector.getGreetMessage());
            return true;
        } else {
            return false;
        }
    }

    public void scanRequests() throws IOException {
        if (!hasConnect()) {
            return;
        }
        String request;
        Command command;
        while (true) {
            try {
                while (!isAuthenticated) {
                    command = consoleReader.readAuthenticationCommand();
                    String res = connector.sendRequest(serializer.serializeCommand(command));
                    if (res.equals(AUTHORIZATION_SUCCESSFUL)) {
                        isAuthenticated = true;
                    }
                    showMessage(res);
                }
                showMessage(ENTER_COMMAND);
                request = consoleReader.readRequest();
                fillCommand(request.toLowerCase());
                if (arg == null) {
                    switch (commandName) {
                        case "add", "add_if_min" -> {
                            command = new Command(commandName, serializer.serializeMusicBand(consoleReader.createBand()));
                            showMessage(connector.sendRequest(serializer.serializeCommand(command)));
                        }
                        case "execute_script" -> {
                            ScriptResult scriptResult = scriptManager.getScriptResult(consoleReader.readPath());
                            ArrayDeque<Command> commands = scriptResult.getCommands();
                            ArrayList<String> errorMessages = scriptResult.getErrorMessages();
                            for (String message : errorMessages) {
                                showMessage(message);
                            }
                            if (errorMessages.isEmpty()) {
                                while (!commands.isEmpty()) {
                                    command = commands.poll();
                                    showMessage(connector.sendRequest(serializer.serializeCommand(command)));
                                }
                            }
                        }
                        case "exit" -> {
                            return;
                        }
                        default -> {
                            command = new Command(commandName);
                            showMessage(connector.sendRequest(serializer.serializeCommand(command)));
                        }
                    }
                } else {
                    ValidationResult validationResult = new ConsoleValidator().isCorrectArg(arg);
                    if (validationResult.isValid()) {
                        if (commandName.equals("update")) {
                            command = new Command(commandName, serializer.serializeMusicBand(consoleReader.createBand()), arg);
                            showMessage(connector.sendRequest(serializer.serializeCommand(command)));
                        } else {
                            command = new Command(commandName, arg);
                            showMessage(connector.sendRequest(serializer.serializeCommand(command)));
                        }
                    } else {
                        showMessage(validationResult.errorMessage());
                    }
                }

            } catch (UserCancelledOperationException e) {
                showMessage(e.getMessage());
            }
        }
    }

    private void fillCommand(String request) {
        String[] commandWithArg = request.split(" ", 2);
        if (commandWithArg.length == 1) {
            commandName = commandWithArg[0];
            arg = null;
        } else {
            commandName = commandWithArg[0];
            arg = commandWithArg[1];
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
