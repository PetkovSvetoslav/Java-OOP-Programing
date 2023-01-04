package Exercises.MilitaryElite.entities;

import Exercises.MilitaryElite.interfaces.Repair;

public class RepairImpl implements Repair {
    private final String partName;
    private final int hoursWorked;

    protected RepairImpl(String partName, int hoursWorked) {
        this.partName = partName;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getPartName() {
        return this.partName;
    }

    @Override
    public int getHoursWorked() {
        return this.hoursWorked;
    }

    @Override
    public String toString() {
        return "Part Name: " + this.partName + " Hours Worked: " + this.hoursWorked;
    }
}
