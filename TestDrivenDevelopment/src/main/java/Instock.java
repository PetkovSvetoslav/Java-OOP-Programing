import java.util.*;
import java.util.stream.Collectors;


public class Instock implements ProductStock {

    private Map<String, Product> products;

    public Instock() {
        this.products = new LinkedHashMap<>();
    }

    @Override
    public Integer getCount() {
        return this.products.size();
    }

    @Override
    public Boolean contains(Product product) {
        return this.products.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        if (!contains(product)) {
            this.products.put(product.getLabel(), product);
        }
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        validateLabel(product);
        validateQuantity(quantity);

        this.products.get(product).setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        return new ArrayList<>(products.values()).get(index);
    }

    @Override
    public Product findByLabel(String label) {
        validateLabel(label);

        return this.products.get(label);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (0 >= count || count > this.products.size()) {
            return new ArrayList<>();
        } else {
            return this.products.values()
                    .stream()
                    .sorted(Comparator.comparing(Product::getLabel))
                    .limit(count)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.products.values()
                .stream()
                .filter(p -> p.getPrice() >= lo && p.getPrice() < hi)
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.products.values()
                .stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (0 > count || count > this.products.size()) {
            throw new IllegalArgumentException("Count is out of bounds: " + count + "!");
        }
        return this.products.values()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.products.values()
                .stream()
                .filter(p -> p.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return products.values().iterator();
    }

    private void validateLabel(String label) {
        if (!this.products.containsKey(label)) {
            throw new IllegalArgumentException("There is no product with such a label");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be a negative number");
        }
    }
}
