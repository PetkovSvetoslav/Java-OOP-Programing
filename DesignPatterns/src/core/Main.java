package core;

import commands.CreateAddress;
import commands.RemoveAddress;
import commands.UpdateAddress;
import repositories.CommandRepository;
import repositories.SingletonContainer;

public class Main {
    static {
        SingletonContainer.init();
    }

    public static void main(String[] args) {
        registerCommands();
        Engine engine = SingletonContainer.getEngine();
        engine.run();
    }

    private static void registerCommands() {
        CommandRepository commands = SingletonContainer.getCommandRepository();
        commands.register("Create", new CreateAddress());
        commands.register("Update", new UpdateAddress());
        commands.register("Remove", new RemoveAddress());
    }
}

