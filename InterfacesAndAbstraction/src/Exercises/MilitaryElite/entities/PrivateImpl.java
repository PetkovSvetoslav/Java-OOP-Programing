package Exercises.MilitaryElite.entities;

import Exercises.MilitaryElite.interfaces.Private;

public class PrivateImpl extends SoldierImpl implements Private {
    private final double salary;

    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    public static PrivateImpl createPrivate(String[] data) {

        int id = Integer.parseInt(data[1]);
        String firstName = data[2];
        String lastName = data[3];
        double salary = Double.parseDouble(data[4]);
        return new PrivateImpl(id, firstName, lastName, salary);
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Salary: %.2f", this.salary);
    }
}
