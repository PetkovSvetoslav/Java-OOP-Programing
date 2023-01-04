package Exercises.MilitaryElite.entities;

import Exercises.MilitaryElite.enums.Corps;
import Exercises.MilitaryElite.enums.State;
import Exercises.MilitaryElite.interfaces.Commando;
import Exercises.MilitaryElite.interfaces.Mission;

import java.util.*;

public class CommandoImpl extends SpecialisedSolderImpl implements Commando {
    private final List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }

    public static CommandoImpl createCommando(String[] data) {
        int id = Integer.parseInt(data[1]);
        String firstName = data[2];
        String lastName = data[3];
        double salary = Double.parseDouble(data[4]);
        String corpsType = data[5];

        Corps corps = Corps.getCrops(corpsType);
        if (corps == null) {
            return null;
        }

        CommandoImpl commando = new CommandoImpl(id, firstName, lastName, salary, corps);

        for (int i = 6; i < data.length; i += 2) {
            String codeName = data[i];
            String stateType = data[i + 1];

            State state = State.getState(stateType);
            if (state != null) {
                Mission mission = new MissionImpl(codeName, state);

                commando.AddMission(mission);
            }
        }
        return commando;
    }

    @Override
    public void AddMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public Collection<Mission> getMission() {
        return Collections.unmodifiableList(this.missions);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(super.toString())
                .append(System.lineSeparator())
                .append("Missions:");

        for (Mission mission : this.missions) {
            out.append(System.lineSeparator())
                    .append("  ").append(mission);
        }
        return out.toString();
    }
}
