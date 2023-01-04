package Lab.vacation;

public enum DiscountType {
    VIP(20, "VIP"),
    SECOND_VISIT(10, "SecondVisit"),
    NONE(0, "None");

    private int percentage;
    private String type;

    DiscountType(int percentage, String type) {
        this.percentage = percentage;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getPercentage() {
        return percentage;
    }
}
