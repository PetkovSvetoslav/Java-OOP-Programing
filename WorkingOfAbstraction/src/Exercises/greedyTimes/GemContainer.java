package Exercises.greedyTimes;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class GemContainer {
    private Map<String, Long> gems;
    private long totalAmount;

    public GemContainer() {
        this.gems = new TreeMap<>(Comparator.reverseOrder());
    }

    public void add(String type, long amount) {
        Long amountOfTHeCurrency = this.gems.get(type);
        if (amountOfTHeCurrency == null) {
            amountOfTHeCurrency = 0L;
        }
        amountOfTHeCurrency += amount;
        this.gems.put(type, amountOfTHeCurrency);
        this.totalAmount += amount;
    }

    public long getTotalAmount() {
        return this.totalAmount;
    }

    public static boolean isGem(String material) {
        return 4 <= material.length() && material.toLowerCase().endsWith("gem");
    }

    @Override
    public String toString() {
        if (this.gems.isEmpty()) {
            return null;
        }
        StringBuilder out = new StringBuilder();

        out.append("<Gem> $").append(this.totalAmount)
                .append(System.lineSeparator());
        for (Map.Entry<String, Long> entry : this.gems.entrySet()) {
            out.append("##").append(entry.getKey()).append(" - ").append(entry.getValue())
                    .append(System.lineSeparator());
        }
        return out.toString();
    }
}
