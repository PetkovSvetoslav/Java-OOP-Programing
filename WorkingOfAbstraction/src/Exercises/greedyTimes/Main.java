package Exercises.greedyTimes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long capacity = Long.parseLong(reader.readLine());
        Bag bag = new Bag(capacity);

        Treasure treasure = new Treasure(reader.readLine().split("\\s+"));

        for (var pair : treasure) {
            String item = pair.getFirst();
            long amount = pair.getSecond();

            if (bag.hasRoomFor(amount)) {
                if (CashContainer.isCash(item)) {
                    bag.addCash(item, amount);
                } else if (GemContainer.isGem(item)) {
                    bag.addGems(item, amount);
                } else if (Gold.isGold(item)) {
                    bag.addGold(amount);
                }
            }
        }
        System.out.println(bag);
    }
}