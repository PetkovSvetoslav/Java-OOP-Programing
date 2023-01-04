package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DummyTest {
    private static final int HEALTH = 10;
    private static final int EXPERIENCE = 10;

    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setUp() {
        this.dummy = new Dummy(HEALTH, EXPERIENCE);
        this.deadDummy = new Dummy(0, EXPERIENCE);
    }

    @Test
    public void testDummyDummyShouldLosesHealthWhenItIsAttacked() {
        this.dummy.takeAttack(5);
        assertEquals(HEALTH - 5, this.dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testDeadDummyShouldThrowsExceptionWhenItIsAttacked() {
        this.deadDummy.takeAttack(1);
    }

    @Test
    public void testDeadDummyGivesExperience() {
        int actualExperience = deadDummy.giveExperience();
        assertEquals(EXPERIENCE, actualExperience);
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyDoesntGiveExperience() {
        this.dummy.giveExperience();
    }
}