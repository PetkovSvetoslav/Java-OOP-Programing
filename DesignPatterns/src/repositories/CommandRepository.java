package repositories;

import commands.Command;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandRepository {
    private Map<String, Command> commands;

    public static CommandRepository instance;

    private CommandRepository() {
        this.commands = new LinkedHashMap<>();
    }

    public static CommandRepository getInstance() {
        if (instance == null) {
            instance = new CommandRepository();
        }
        return instance;
    }

    public void register(String commandName, Command command) {
        this.commands.put(commandName, command);
    }

    public Command getCommand(String command) {
        return this.commands.get(command);
    }


}
