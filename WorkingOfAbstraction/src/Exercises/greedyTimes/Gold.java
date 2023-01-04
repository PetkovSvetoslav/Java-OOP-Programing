package Exercises.greedyTimes;

public class Gold {
    private Long amount;

    public Gold() {
    }

    public void add(long amount) {
        if (this.amount == null) {
            this.amount = 0L;
        }
        this.amount += amount;
    }

    public Long getTotalAmount() {
        return this.amount;
    }

    public static boolean isGold(String material) {
        return material.equalsIgnoreCase("gold");
    }

    @Override
    public String toString() {
        if (this.amount == null) {
            return null;
        }
        return "<Gold> $" + this.amount + System.lineSeparator()
                + "##Gold - " + this.amount + System.lineSeparator();
    }
}
