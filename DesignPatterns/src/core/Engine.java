package core;

import repositories.CommandRepository;
import repositories.SingletonContainer;

import java.io.BufferedReader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Engine {
    private BufferedReader reader;
    private Writer writer;
    private CommandRepository commands;

    private static Engine instance;

    private Engine(BufferedReader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
        this.commands = SingletonContainer.getCommandRepository();
    }

    public static Engine getInstance(BufferedReader reader, Writer writer) {
        if (instance == null) {
            instance = new Engine(reader, writer);
        }
        return instance;
    }

    public void run() {
        try {
            String line;
            while (!"End".equals(line = reader.readLine())) {
                String[] tokens = line.split(" -> ");

                String command = tokens[0];
                String output = commands.getCommand(command).execute(tokens[1]);

                writer.write(output + System.lineSeparator());
                writer.flush();
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
    }
}
