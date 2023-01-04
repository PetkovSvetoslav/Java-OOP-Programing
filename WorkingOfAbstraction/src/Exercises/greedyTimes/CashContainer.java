package Exercises.greedyTimes;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CashContainer {
    private Map<String, Long> cash;
    private long totalAmount;

    public CashContainer() {
        this.cash = new TreeMap<>(Comparator.reverseOrder());
    }

    public void add(String type, long amount) {
        Long amountOfTheGem = this.cash.get(type);
        if (amountOfTheGem == null) {
            amountOfTheGem = 0L;
        }
        amountOfTheGem += amount;
        this.cash.put(type, amountOfTheGem);
        this.totalAmount += amount;
    }

    public long getTotalAmount() {
        return this.totalAmount;
    }

    public static boolean isCash(String currency) {
        return currency.length() == 3;
    }

    @Override
    public String toString() {
        if (this.cash.isEmpty()) {
            return null;
        }
        StringBuilder out = new StringBuilder();

        out.append("<Cash> $").append(this.totalAmount)
                .append(System.lineSeparator());
        for (Map.Entry<String, Long> entry : this.cash.entrySet()) {
            out.append("##").append(entry.getKey()).append(" - ").append(entry.getValue())
                    .append(System.lineSeparator());
        }
        return out.toString();
    }
}
