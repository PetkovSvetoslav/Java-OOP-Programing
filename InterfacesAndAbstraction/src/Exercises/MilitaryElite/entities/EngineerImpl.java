package Exercises.MilitaryElite.entities;

import Exercises.MilitaryElite.enums.Corps;
import Exercises.MilitaryElite.interfaces.Engineer;
import Exercises.MilitaryElite.interfaces.Repair;

import java.util.*;

public class EngineerImpl extends SpecialisedSolderImpl implements Engineer {
    private final List<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
    }

    public static EngineerImpl createEngineer(String[] data) {

        int id = Integer.parseInt(data[1]);
        String firstName = data[2];
        String lastName = data[3];
        double salary = Double.parseDouble(data[4]);
        String corpsType = data[5];

        Corps corps = Corps.getCrops(corpsType);
        if (corps == null) {
            return null;
        }

        EngineerImpl engineer = new EngineerImpl(id, firstName, lastName, salary, corps);

        for (int i = 6; i < data.length; i += 2) {
            String partName = data[i];
            int hoursWorked = Integer.parseInt(data[i + 1]);

            RepairImpl repair = new RepairImpl(partName, hoursWorked);
            engineer.addRepair(repair);
        }
        return engineer;
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return Collections.unmodifiableList(this.repairs);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(super.toString())
                .append(System.lineSeparator())
                .append("Repairs:");

        for (Repair repair : this.repairs) {
            out.append(System.lineSeparator())
                    .append("  ").append(repair);
        }

        return out.toString();
    }
}
