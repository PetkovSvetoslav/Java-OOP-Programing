package repositories;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PopulationTracker {
    private Map<String, Long> counts;
    private static PopulationTracker instance;

    private PopulationTracker() {
        this.counts = new LinkedHashMap<>();
    }

    public void add(String location, long count) {
        Long currentCount = this.counts.get(location);

        if (currentCount == null) {
            currentCount = 0L;
        }
        currentCount += count;

        this.counts.put(location, currentCount);
    }

    public void remove(String location, long count) {
        Long currentCount = this.counts.get(location);

        if (currentCount == null) {
            currentCount = 0L;
        }
        currentCount = Math.max(0, currentCount - count);

        this.counts.put(location, currentCount);
    }

    public Map<String, Long> getCounts() {
        return Collections.unmodifiableMap(this.counts);
    }

    public static PopulationTracker getInstance() {
        if (instance == null) {
            instance = new PopulationTracker();
        }
        return instance;
    }
}
