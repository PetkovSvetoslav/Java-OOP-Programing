package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish {
    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        increaseSize(5);
    }

    @Override
    public void eat() {
        increaseSize(2);
    }
}
