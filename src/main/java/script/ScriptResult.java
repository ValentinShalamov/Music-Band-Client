package script;

import command.Command;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class ScriptResult {
    private final ArrayList<String> errorMessages;
    private final ArrayDeque<Command> commands;

    public ScriptResult() {
        this.errorMessages = new ArrayList<>();
        this.commands = new ArrayDeque<>();
    }

    public ArrayDeque<Command> getCommands() {
        return commands;
    }

    public ArrayList<String> getErrorMessages() {
        return errorMessages;
    }

    public void addErrorMessages(String message) {
        errorMessages.add(message);
    }

    public void addCommand(Command command) {
        commands.add(command);
    }
}
