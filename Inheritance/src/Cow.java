public class Cow extends Organism {
    private String name;
    private int weight;

    public Cow(String name, int weight) {
        super(name, weight);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println(" grass");
    }
}
