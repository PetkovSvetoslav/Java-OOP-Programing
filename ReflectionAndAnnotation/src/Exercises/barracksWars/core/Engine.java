package Exercises.barracksWars.core;

import Exercises.barracksWars.interfaces.CommandInterpreter;
import Exercises.barracksWars.interfaces.Repository;
import Exercises.barracksWars.interfaces.Runnable;
import Exercises.barracksWars.interfaces.UnitFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {
    private CommandInterpreter commandInterpreter;

    public Engine(Repository repository, UnitFactory unitFactory) {
        this.commandInterpreter = new CommandInterpreterImpl(repository, unitFactory);
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = commandInterpreter.interpretCommand(data, commandName).execute();
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
