package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.print.attribute.standard.MediaSize;

import static org.junit.Assert.*;

public class SpaceshipTests {
    private Spaceship spaceship;
    private static final String SPACESHIP_NAME = "Polaris";
    private static final int CAPACITY = 2;

    @Before
    public void setUp() {
        this.spaceship = new Spaceship(SPACESHIP_NAME, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void SpaceshipConstructor_NullAsName_ThrowsException() {
        final String NULL = null;

        new Spaceship(NULL, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void SpaceshipConstructor_EmptyName_TrowsException() {
        final String EMPTY_NAME = "";

        new Spaceship(EMPTY_NAME, CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SpaceshipConstructor_NegativeCapacity_ThrowsException() {
        final int NEGATIVE_CAPACITY = -1;

        new Spaceship(SPACESHIP_NAME, NEGATIVE_CAPACITY);
    }

    @Test
    public void SpaceshipConstructor_CorrectName_SetsTheNameCorrectly() {
        String name = this.spaceship.getName();

        assertEquals(name, SPACESHIP_NAME);
    }

    @Test
    public void Add_SingleAstronaut_IncreasesTheNumberOfCrewWithOne() {
        createAndAddAstronaut("Jhon", 42.0);

        int spaceshipCount = this.spaceship.getCount();

        assertEquals(1, spaceshipCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Add_ThreeAstronauts_OverflowAndThrowsException() {
        final double OXYGEN_IN_PERCENTAGE = 99.9;

        createAndAddAstronaut("Angel", OXYGEN_IN_PERCENTAGE);
        createAndAddAstronaut("Peter", OXYGEN_IN_PERCENTAGE);
        createAndAddAstronaut("James", OXYGEN_IN_PERCENTAGE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Add_TwoAstronautsWithTheSameName_ThrowsException() {
        final String NAME = "Georgi";

        createAndAddAstronaut(NAME, 55.0);
        createAndAddAstronaut(NAME, 100.0);
    }

    @Test
    public void Remove_EmptySpaceship_ReturnsFalse() {
        final String NAME = "Neil";

        boolean remove = this.spaceship.remove(NAME);

        assertFalse(remove);
    }

    @Test
    public void Remove_WithWrongName_ReturnsFalse() {
        final String NAME = "Alex";
        final String WRONG_NAME = "Aleks";

        createAndAddAstronaut(NAME, 79.0);

        this.spaceship.remove(WRONG_NAME);
    }

    @Test
    public void Remove_FromThreeAstronauts_RemovesTheSecondOne() {
        this.spaceship = new Spaceship(SPACESHIP_NAME, 3);

        final double OXYGEN_IN_PERCENTAGE = 88.0;
        final String NAME = "Mike";

        createAndAddAstronaut("Ben", OXYGEN_IN_PERCENTAGE);
        createAndAddAstronaut(NAME, OXYGEN_IN_PERCENTAGE);
        createAndAddAstronaut("Ray", OXYGEN_IN_PERCENTAGE);

        boolean remove = this.spaceship.remove(NAME);
        assertTrue(remove);

        remove = this.spaceship.remove(NAME);
        assertFalse(remove);
    }

    private Astronaut createAndAddAstronaut(String name, double oxygenInPercentage) {
        Astronaut astronaut = new Astronaut(name, oxygenInPercentage);
        this.spaceship.add(astronaut);

        return astronaut;
    }
}
