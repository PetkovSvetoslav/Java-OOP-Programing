package Exercises.barracksWars.core;

import Exercises.barracksWars.interfaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private static final String COMMANDS_PACKAGE_NAME = "Exercises.barracksWars.core.commands.";

    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {
        try {
            Class<?> clazz = Class.forName(getCommandClassName(COMMANDS_PACKAGE_NAME, commandName));
            Constructor<?> constructor = clazz.getConstructor(String[].class);

            Executable executable = (Executable) constructor.newInstance((Object) data);

            setFields(executable);

            return executable;

        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Invalid command!");
    }

    private void setFields(Executable executable) throws IllegalAccessException {
        Class<? extends Executable> executableClass = executable.getClass();
        Field[] executableFields = executableClass.getDeclaredFields();
        Field[] localFields = this.getClass().getDeclaredFields();

        for (Field field : executableFields) {
            Inject annotation = field.getAnnotation(Inject.class);

            if (annotation != null) {
                field.setAccessible(true);

                for (Field localField : localFields) {
                    if (field.getType() == localField.getType()) {
                        field.set(executable, localField.get(this));
                    }
                }
            }
        }
    }

    private static String getCommandClassName(String path, String commandName) {
        char firstLetter = Character.toUpperCase(commandName.charAt(0));
        String substring = commandName.substring(1);

        return path + firstLetter + substring + "Command";
    }
}
