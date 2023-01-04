package Exercises.MilitaryElite.entities;

import Exercises.MilitaryElite.interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private final String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    public static SpyImpl createSpy(String[] data) {

        int id = Integer.parseInt(data[1]);
        String firstName = data[2];
        String lastName = data[3];
        String codeNumber = data[4];

        return new SpyImpl(id, firstName, lastName, codeNumber);
    }

    @Override
    public String getCodeNumber() {
        return this.codeNumber;
    }

    @Override
    public String toString() {
        return super.toString()
                + System.lineSeparator()
                + "Code Number: " + this.codeNumber;
    }
}
