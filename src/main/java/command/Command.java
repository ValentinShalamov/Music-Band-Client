package command;

public class Command {
    private final String name;
    private final String firstArg;
    private final String secondArg;

    public Command(String name) {
        this.name = name;
        this.firstArg = null;
        this.secondArg = null;
    }

    public Command(String name, String firstArg) {
        this.name = name;
        this.firstArg = firstArg;
        this.secondArg = null;
    }

    public Command(String name, String firstArg, String secondArg) {
        this.name = name;
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

}
