package heroRepository;

import org.junit.Assert;
import org.junit.Test;

public class HeroRepositoryTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository
    @Test
    public void testCreate(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero = new Hero("i", 1);
        Hero hero1 = new Hero("ii", 1);
        heroRepository.create(hero);
        heroRepository.create(hero1);
        Assert.assertEquals(2,heroRepository.getCount());
    }
    @Test(expected = NullPointerException.class)
    public void testCreate1(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero = new Hero(null,1);
        heroRepository.create(null);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreate12(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero = new Hero("d",1);
        heroRepository.create(hero);
        heroRepository.create(hero);
    }
    @Test(expected = NullPointerException.class)
    public void testRemove(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero = new Hero(null,1);
        heroRepository.create(hero);
        heroRepository.remove(null);

    }
    @Test(expected = NullPointerException.class)
    public void testRemove0(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero = new Hero(" ",1);
        heroRepository.create(hero);
        heroRepository.remove(" ");

    }
    @Test
    public void testRemove1(){
        HeroRepository heroRepository = new HeroRepository();
        Hero hero = new Hero("d",1);
        heroRepository.create(hero);
        Assert.assertEquals(1,heroRepository.getCount());
        heroRepository.remove("d");
        Assert.assertEquals(0,heroRepository.getCount());
    }
@Test
    public void testgetHeroWithHighestLevel(){
    HeroRepository heroRepository = new HeroRepository();
    Hero hero = new Hero("d",1);
    heroRepository.create(hero);
    Hero hero1 = new Hero("dd",3);
    heroRepository.create(hero1);
    Hero hero2 = new Hero("ddd",2);
    heroRepository.create(hero2);
    Assert.assertEquals(hero1.getName(), heroRepository.getHeroWithHighestLevel().getName());
    Assert.assertEquals(hero1.getLevel(), heroRepository.getHeroWithHighestLevel().getLevel());
    Assert.assertEquals(3,heroRepository.getHeroes().size());

}
@Test
    public void testgetHero(){
    HeroRepository heroRepository = new HeroRepository();
    Hero hero = new Hero("d",1);
    heroRepository.create(hero);
    Hero hero1 = new Hero("dd",3);
    heroRepository.create(hero1);
    heroRepository.remove("dd");
    Hero hero2 = heroRepository.getHero("dd");

    Assert.assertNull(hero2);
}

}
