package Exercises.FoodShortage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Buyer> buyersByName = new HashMap<>();

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] buyerData = reader.readLine().split("\\s+");

            Buyer buyer = createBuyer(buyerData);
            if (buyer != null) {
                buyersByName.put(buyer.getName(), buyer);
            }
        }

        String name;
        while (!"End".equals(name = reader.readLine())) {
            Buyer buyer = buyersByName.get(name);

            if (buyer != null) {
                buyer.buyFood();
            }
        }

        System.out.println(buyersByName.values()
                .stream()
                .mapToInt(Buyer::getFood)
                .sum());
    }

    private static Buyer createBuyer(String[] buyerData) {
        String name = buyerData[0];
        int age = Integer.parseInt(buyerData[1]);

        switch (buyerData.length) {
            case 3:
                String group = buyerData[2];

                return new Rebel(name, age, group);
            case 4:
                String id = buyerData[2];
                String birthdate = buyerData[3];

                return new Citizen(name, age, id, birthdate);
        }
        return null;
    }
}
