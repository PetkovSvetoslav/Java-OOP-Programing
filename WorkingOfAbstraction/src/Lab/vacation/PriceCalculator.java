package Lab.vacation;

public class PriceCalculator {
    private double pricePerDay;
    private int days;
    private Season season;
    private DiscountType discount;

    public PriceCalculator(double pricePerDay, int days, Season season, DiscountType discount) {
        this.pricePerDay = pricePerDay;
        this.days = days;
        this.season = season;
        this.discount = discount;
    }

    public double calculate() {
        return this.pricePerDay * this.days * season.getMultiplier() * (1 - ((double) discount.getPercentage() / 100));
    }
}
