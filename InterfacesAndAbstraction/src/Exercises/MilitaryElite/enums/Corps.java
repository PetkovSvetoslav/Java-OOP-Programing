package Exercises.MilitaryElite.enums;

public enum Corps {
    AIRFORCES("Airforces"),
    MARINES("Marines");

    private final String name;

    Corps(String name) {
        this.name = name;
    }

    public static Corps getCrops(String cropsName) {
        for (Corps value : Corps.values()) {
            if (value.getName().equals(cropsName)) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

}
