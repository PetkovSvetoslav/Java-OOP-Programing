package Exercises.barracksWars.core.commands;

import Exercises.barracksWars.interfaces.Executable;

import java.util.Arrays;

public abstract class Command implements Executable {
    private String[] data;

    protected Command(String[] data) {
        this.data = data;
    }

    protected String[] getData() {
        return Arrays.copyOf(this.data, this.data.length);
    }
}
