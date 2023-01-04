package Exercises.MilitaryElite.interfaces;

import Exercises.MilitaryElite.enums.State;

public interface Mission {

    String getCodeName();

    State getSate();

    void completeMission();

    boolean isFinished();
}
