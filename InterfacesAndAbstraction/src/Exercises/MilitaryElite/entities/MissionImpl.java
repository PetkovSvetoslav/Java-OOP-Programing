package Exercises.MilitaryElite.entities;

import Exercises.MilitaryElite.enums.State;
import Exercises.MilitaryElite.interfaces.Mission;

public class MissionImpl implements Mission {
    private final String codeName;
    private State state;

    protected MissionImpl(String codeName, State state) {
        this.codeName = codeName;
        this.state = state;
    }

    @Override
    public String getCodeName() {
        return this.codeName;
    }

    @Override
    public State getSate() {
        return this.state;
    }

    @Override
    public void completeMission() {
        this.state = State.FINISHED;
    }

    @Override
    public boolean isFinished() {
        return this.state == State.FINISHED;
    }

    @Override
    public String toString() {
        return "Code Name: " + this.codeName + " State: " + this.state.getName();
    }
}
