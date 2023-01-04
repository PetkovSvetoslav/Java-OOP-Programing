package Exercises;

public enum TrafficLights {
    GREEN("YELLOW"),
    YELLOW("RED"),
    RED("GREEN");

    private String nextLight;

    TrafficLights(String nextLight) {
        this.nextLight = nextLight;
    }

    public String getNextLight() {
        return this.nextLight;
    }
}
