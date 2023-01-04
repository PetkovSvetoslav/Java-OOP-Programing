package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroTest {
    /*
    private static class TestWeapon implements Weapon {
        @Override
        public int getAttackPoints() {
            return 0;
        }

        @Override
        public int getDurabilityPoints() {
            return 0;
        }

        @Override
        public void attack(Target target) {

        }
    }

    private static class TestTarget implements Target {

        @Override
        public int getHealth() {
            return 0;
        }

        @Override
        public void takeAttack(int attackPoints) {

        }

        @Override
        public int giveExperience() {
            return EXPERIENCE;
        }

        @Override
        public boolean isDead() {
            return true;
        }
    }
     */

    private final static int EXPERIENCE = 45;

    private Hero hero;

    @Before
    public void setUp() {
        Weapon weapon = mock(Weapon.class);
        this.hero = new Hero("Gandalf", weapon);
    }

    @Test
    public void testHeroGainsExperienceWhenTargetIsKilled() {
        Target target = mock(Target.class);
        when(target.giveExperience()).thenReturn(EXPERIENCE);
        when(target.isDead()).thenReturn(true);

        this.hero.attack(target);
        assertEquals(EXPERIENCE, this.hero.getExperience());
    }

}