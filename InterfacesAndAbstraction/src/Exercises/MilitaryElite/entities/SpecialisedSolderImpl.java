package Exercises.MilitaryElite.entities;

import Exercises.MilitaryElite.enums.Corps;
import Exercises.MilitaryElite.interfaces.SpecialisedSolder;

public abstract class SpecialisedSolderImpl extends PrivateImpl implements SpecialisedSolder {
    private final Corps corps;

    protected SpecialisedSolderImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary);
        this.corps = corps;
    }

    @Override
    public Corps getCorps() {
        return this.corps;
    }

    @Override
    public String toString() {
        return super.toString()
                + System.lineSeparator()
                + "Corps: " + this.corps.getName();
    }
}
