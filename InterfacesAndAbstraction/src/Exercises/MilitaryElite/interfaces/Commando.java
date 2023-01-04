package Exercises.MilitaryElite.interfaces;

import java.util.Collection;

public interface Commando {

    void AddMission(Mission mission);

    Collection<Mission> getMission();
}
