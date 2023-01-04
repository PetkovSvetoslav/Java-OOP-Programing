package Lab;

import Lab.vacation.DiscountType;
import Lab.vacation.PriceCalculator;
import Lab.vacation.Season;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HotelReservation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] reservationInfo = reader.readLine().split("\\s+");
        double pricePerDay = Double.parseDouble(reservationInfo[0]);
        int numberOfDays = Integer.parseInt(reservationInfo[1]);
        Season season = Season.valueOf(reservationInfo[2].toUpperCase());
        DiscountType discount = findDiscount(reservationInfo[3]);

        PriceCalculator calculator = new PriceCalculator(pricePerDay, numberOfDays
                , season, discount);

        System.out.printf("%.2f%n", calculator.calculate());
    }

    private static DiscountType findDiscount(String discountType) {
        for (DiscountType value : DiscountType.values()) {
            if (value.getType().equals(discountType)) {
                return value;
            }
        }
        return null;
    }
}
