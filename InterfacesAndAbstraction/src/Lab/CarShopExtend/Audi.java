package Lab.CarShopExtend;

public class Audi extends CarImpl implements Rentable {
    private Integer minRentPerDay;
    private Double pricePerDay;

    public Audi(String model, String color, Integer horsePower, String countryProduced
            , Integer minRentPerDay, Double pricePerDay) {

        super(model, color, horsePower, countryProduced);
        this.minRentPerDay = minRentPerDay;
        this.pricePerDay = pricePerDay;
    }

    @Override
    public Integer getMinRentDay() {
        return this.minRentPerDay;
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
    }

    @Override
    public String toString() {
        return super.toString()
                + System.lineSeparator()
                + String.format("Minimum rental period of %d days. " +
                        "Price per day %f"
                , this.minRentPerDay, this.pricePerDay);
    }
}
