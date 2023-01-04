package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AxeTest {
    private final static int ATTACK = 10;
    private final static int DURABILITY = 10;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void setUp() {
        this.axe = createAxe(ATTACK, DURABILITY);
        this.dummy = createDummy();
    }

    private Axe createAxe(int attack, int durability) {
        return new Axe(attack, durability);
    }

    private Dummy createDummy() {
        return new Dummy(10, 10);
    }

    @Test
    public void testAxeConstructorShouldSetCorrectAttackAndDurability() {
        assertEquals(ATTACK, this.axe.getAttackPoints());
        assertEquals(DURABILITY, this.axe.getDurabilityPoints());
    }

    @Test
    public void testAxeShouldLoosesSingleDurabilityPointWhenAttackingDummy() {
        this.axe.attack(this.dummy);
        assertEquals(DURABILITY - 1, this.axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAxeAttackShouldFailIfAxeHasZeroDurability() {
        Axe axe = createAxe(ATTACK, 0);
        axe.attack(this.dummy);
    }
}
