package Lab;

public enum DayOfWeek {
    MONDAY(1, "Monday"),
    TUESDAY(2, "Tuesday"),
    WEDNESDAY(3, "Wednesday"),
    THURSDAY(4, "Thursday"),
    FRIDAY(5, "Friday"),
    SATURDAY(6, "Saturday"),
    SUNDAY(7, "Sunday");

    private int dayAsInt;
    private String name;

    DayOfWeek(int dayAsInt, String name) {
        this.dayAsInt = dayAsInt;
        this.name = name;
    }

    public int getDayAsInt() {
        return dayAsInt;
    }

    public String getName() {
        return name;
    }
}
