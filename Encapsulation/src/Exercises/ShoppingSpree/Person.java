package Exercises.ShoppingSpree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setMoney(double money) {
        validateMoney(money);
        this.money = money;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(this.products);
    }

    public void buyProduct(Product product) {
        if (!hasEnoughMoneyFor(product)) {
            throw new IllegalStateException
                    (this.name + " can't afford " + product.getName());
        }

        this.products.add(product);
        this.money -= product.getCost();
    }

    public boolean hasEnoughMoneyFor(Product product) {
        return (product.getCost() <= this.money);
    }

    @Override
    public String toString() {
        return this.products.isEmpty()
                ? this.name + " - Nothing bought"
                : this.name + " - " + formatProducts();
    }

    private String formatProducts() {
        return this.getProducts()
                .stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
    }

    private void validateMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
