package Exercises.greedyTimes;

public class Bag {
    private Gold gold;
    private GemContainer gemContainer;
    private CashContainer cashContainer;

    private long capacity;

    public Bag(long capacity) {
        this.gold = new Gold();
        this.gemContainer = new GemContainer();
        this.cashContainer = new CashContainer();
        this.capacity = capacity;
    }

    public void addGold(long amount) {
        this.gold.add(amount);
        decreaseCapacity(amount);
    }

    public void addGems(String kind, long amount) {
        if (canAddGems(amount)) {
            this.gemContainer.add(kind, amount);
            decreaseCapacity(amount);
        }
    }

    public void addCash(String currency, long amount) {
        if (canAddCash(amount)) {
            this.cashContainer.add(currency, amount);
            decreaseCapacity(amount);
        }
    }

    private boolean canAddGems(long gemsAmount) {
        return this.gold.getTotalAmount() != null
                && this.gemContainer.getTotalAmount() + gemsAmount <= this.gold.getTotalAmount();
    }

    private boolean canAddCash(long cashAmount) {
        return this.cashContainer.getTotalAmount() + cashAmount <= gemContainer.getTotalAmount();
    }

    private void decreaseCapacity(long amount) {
        this.capacity -= amount;
    }

    public boolean hasRoomFor(long itemAmount) {
        return this.capacity - itemAmount >= 0;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        if (this.gold.toString() != null) {
            out.append(this.gold);
        }

        if (this.gemContainer.toString() != null) {
            out.append(this.gemContainer);
        }

        if (this.cashContainer.toString() != null) {
            out.append(this.cashContainer);
        }

        return out.toString().trim();
    }
}
