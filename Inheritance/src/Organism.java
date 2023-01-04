public class Organism {
    private String name;
    private int weight;

    public Organism(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public void eat() {
        System.out.print("Eating");
    }
}
