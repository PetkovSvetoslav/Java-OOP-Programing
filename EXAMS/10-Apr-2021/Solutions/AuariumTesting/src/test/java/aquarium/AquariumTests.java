package aquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AquariumTests {
    private static final String AQUARIUM_NAME = "Box";
    private static final int CAPACITY = 3;

    private Aquarium aquarium;

    @Before
    public void setUp() {
        this.aquarium = new Aquarium(AQUARIUM_NAME, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void AquariumConstructor_NullAsName_ThrowsException() {
        final String NULL_NAME = null;

        new Aquarium(NULL_NAME, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void AquariumConstructor_EmptyName_ThrowsException() {
        final String EMPTY_NAME = "";

        new Aquarium(EMPTY_NAME, CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AquariumConstructor_NegativeCapacity_ThrowsException() {
        final int NEGATIVE_CAPACITY = -1;

        new Aquarium(AQUARIUM_NAME, NEGATIVE_CAPACITY);
    }

    @Test
    public void AquariumConstructor_CorrectValues_ReturnsSameValues() {
        String aquariumName = this.aquarium.getName();
        int aquariumCapacity = this.aquarium.getCapacity();

        assertEquals(AQUARIUM_NAME, aquariumName);
        assertEquals(CAPACITY, aquariumCapacity);
    }

    @Test
    public void Add_SingleFish_IncreasesTheCount() {
        int count = this.aquarium.getCount();
        assertEquals(0, count);

        createAndAddFish("Pepe");

        count = this.aquarium.getCount();
        assertEquals(1, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Add_MoreFishThenCapacity_ThrowsException() {
        final int CAPACITY = 1;

        this.aquarium = new Aquarium(AQUARIUM_NAME, CAPACITY);
        createAndAddFish("Ronaldo");
        createAndAddFish("Ronnie");
    }

    @Test(expected = IllegalArgumentException.class)
    public void Remove_NonExistentFish_ThrowsException() {
        final String NAME = "Michel";
        final String FISH_TO_REMOVE = "Mikel";

        createAndAddFish(NAME);
        this.aquarium.remove(FISH_TO_REMOVE);
    }

    @Test
    public void Remove_TwoAddedFish_DecreaseTheCount() {
        final String FISH_NAME = "Messi";

        createAndAddFish(FISH_NAME);
        createAndAddFish("Gregor");

        this.aquarium.remove(FISH_NAME);

        int count = this.aquarium.getCount();

        assertEquals(1, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SellFish_NonExistentFish_ThrowsException() {
        final String NAME = "Michel";
        final String FISH_TO_REMOVE = "Mikel";

        createAndAddFish(NAME);
        this.aquarium.sellFish(FISH_TO_REMOVE);
    }

    @Test
    public void SellFish_TwoAddedFish_RerunsTheFirstOne() {
        final String FISH_TO_SELL = "Barry";

        Fish firstFish = createAndAddFish(FISH_TO_SELL);
        Fish secondFish = createAndAddFish("SnoopDogg");

        Fish soldFish = this.aquarium.sellFish(FISH_TO_SELL);

        assertFalse(firstFish.isAvailable());
        assertEquals(firstFish, soldFish);
    }

    @Test
    public void SellFish_TwoAddedFish_MakesSecondFishInaccessible() {
        final String FISH_TO_SELL = "Barry";

        Fish firstFish = createAndAddFish("SnoopDogg");
        Fish secondFish = createAndAddFish(FISH_TO_SELL);

        this.aquarium.sellFish(FISH_TO_SELL);

        assertTrue(firstFish.isAvailable());
        assertFalse(secondFish.isAvailable());
    }

    @Test
    public void Report_TwoAddedFish_ReturnCorrectData() {
        final String FIRST_NAME = "Pesho";
        final String SECOND_NAME = "Gosho";

        createAndAddFish(FIRST_NAME);
        createAndAddFish(SECOND_NAME);

        final String EXPECTED_REPORT = String.format("Fish available at %s: %s",
                AQUARIUM_NAME, FIRST_NAME + ", " + SECOND_NAME);

        String report = this.aquarium.report();

        assertEquals(EXPECTED_REPORT, report);
    }

    private Fish createAndAddFish(String name) {
        Fish fish = new Fish(name);
        this.aquarium.add(fish);
        return fish;
    }
}

