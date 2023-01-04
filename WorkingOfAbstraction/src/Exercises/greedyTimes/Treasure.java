package Exercises.greedyTimes;

import java.util.Iterator;

public class Treasure implements Iterable<Treasure.Pair<String, Long>> {
    public static class Pair<K, V> {
        private final K first;
        private final V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {
            return this.first;
        }

        public V getSecond() {
            return this.second;
        }
    }

    public class TreasureIterator implements Iterator<Pair<String, Long>> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return this.index < goods.length;
        }

        @Override
        public Pair<String, Long> next() {
            String item = goods[this.index++];
            long amount = Long.parseLong(goods[this.index++]);
            return new Pair<>(item, amount);
        }
    }


    private final String[] goods;

    public Treasure(String[] goods) {
        this.goods = goods;
    }

    public Iterator<Pair<String, Long>> iterator() {
        return new TreasureIterator();
    }
}
