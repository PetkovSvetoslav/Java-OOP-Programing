package Exercises.word;

public class Initialization {
    public static CommandInterface buildCommandInterface(StringBuilder text) {
        CommandInterface command = new CommandImpl(text);
        command.init();
        return command;
    }
}
