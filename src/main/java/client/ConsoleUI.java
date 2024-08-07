package client;

import command.Command;
import command.CommandSerializer;
import exceptions.UserCancelledOperationException;
import script.ScriptManager;
import script.ScriptResult;
import validator.ConsoleValidator;
import validator.ValidationResult;

import java.io.*;
import java.net.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

import static messages.ConnectionMessages.*;
import static messages.UserMessages.ENTER_COMMAND;

public class ConsoleUI {
    private final ConsoleReader consoleReader;
    private String commandName;
    private String arg;
    private final InetAddress address;
    private final int port;
    private final char[] chars;
    private Reader reader;
    private Writer writer;
    private final CommandSerializer serializer;
    private final ScriptManager scriptManager;

    public ConsoleUI(InetAddress address, int port) {
        this.consoleReader = new ConsoleReader();
        this.commandName = "";
        this.arg = "";
        this.address = address;
        this.port = port;
        this.chars = new char[1024 * 1024];
        this.serializer = new CommandSerializer();
        this.scriptManager = new ScriptManager();
    }

    public void start() {
        try (Socket socket = new Socket()) {
            showMessage(WAITING_CONNECTION);
            socket.connect(new InetSocketAddress(address, port), 10000);
            try (InputStream inStream = socket.getInputStream();
                 OutputStream outStream = socket.getOutputStream()) {
                try(InputStreamReader inReader = new InputStreamReader(inStream);
                    OutputStreamWriter outWriter = new OutputStreamWriter(outStream)) {
                    reader = inReader;
                    writer = outWriter;
                    showMessage(SUCCESSFUL_CONNECT + address.getHostAddress() + ":" + port);
                    int r = reader.read(chars);
                    showMessage(new String(chars, 0, r));
                    startRequestHandler();
                }
            }
        } catch (UnknownHostException e) {
            showMessage(HOST_HAS_NOT_DETERMINED);
        } catch (ConnectException e) {
            showMessage(e.getMessage());
        } catch (SocketTimeoutException e) {
            showMessage(CONNECTION_TIMEOUT);
        } catch (IOException e) {
            showMessage(e.getMessage());
            showMessage(UNEXPECTED_ERROR);
        }
    }

    private void startRequestHandler() throws IOException {
        String request;
        Command command;
        while (true) {
            try {
                showMessage(ENTER_COMMAND);
                request = consoleReader.readRequest();
                fillCommand(request.toLowerCase());
                if (arg == null) {
                    switch (commandName) {
                        case "add", "add_if_min" -> {
                            command = new Command(commandName, serializer.serializeMusicBand(consoleReader.createBand()));
                            showMessage(sendRequest(serializer.parseString(command)));
                        }
                        case "filter_by_best_album" -> {
                            command = new Command(commandName, serializer.serializeBestAlbum(consoleReader.readBestAlbum()));
                            showMessage(sendRequest(serializer.parseString(command)));
                        }
                        case "execute_script" -> {
                            ScriptResult scriptResult = scriptManager.getScriptResult(consoleReader.readPath());
                            ArrayDeque<Command> commands = scriptResult.getCommands();
                            ArrayList<String> errorMessages = scriptResult.getErrorMessages();
                            for (String message : errorMessages) {
                                showMessage(message);
                            }
                            while (!commands.isEmpty()) {
                                command = commands.poll();
                                showMessage(sendRequest(serializer.parseString(command)));
                            }
                        }
                        case "exit" -> {
                            return;
                        }
                        default -> {
                            command = new Command(commandName);
                            showMessage(sendRequest(serializer.parseString(command)));
                        }
                    }
                } else {
                    ValidationResult validationResult = new ConsoleValidator().isCorrectArg(arg);
                    if (validationResult.isValid()) {
                        if (commandName.equals("update")) {
                            command = new Command(commandName, serializer.serializeMusicBand(consoleReader.createBand()), arg);
                            showMessage(sendRequest(serializer.parseString(command)));
                        } else {
                            command = new Command(commandName, arg);
                            showMessage(sendRequest(serializer.parseString(command)));
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

    private String sendRequest(String request) throws IOException {
        writer.write(request);
        writer.flush();
        int r = reader.read(chars);
        if (r == -1) {
            throw new ConnectException(CONNECTION_HAS_DIED);
        }
        return new String(chars, 0, r);
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

    private void showMessage(String message) {
        System.out.println(message);
    }

}
