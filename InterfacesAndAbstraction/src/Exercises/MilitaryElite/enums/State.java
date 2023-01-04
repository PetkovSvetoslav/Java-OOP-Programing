package Exercises.MilitaryElite.enums;

public enum State {
    IN_PROGRESS("inProgress"),
    FINISHED("finished");

    private final String name;

    State(String name) {
        this.name = name;
    }

    public static State getState(String stateName) {
        for (State value : State.values()) {
            if (value.getName().equals(stateName)) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}
