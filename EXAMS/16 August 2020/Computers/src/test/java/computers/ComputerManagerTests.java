package computers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ComputerManagerTests {
//    // TODO: Test ComputerManager
//
//    @Test
//    public void test(){
//        List<Computer> computerList = new ArrayList<>();
//Computer computer = new Computer("A","B",10);
//Computer computer1 = new Computer("C","D",20);
//computerList.add(computer);
//computerList.add(computer1);
//        Assert.assertEquals(2,computerList.size());
//        Assert.assertEquals("A",computer.getManufacturer());
//        Assert.assertEquals("B",computer.getModel());
//    }
//    @Test
//    public void test1(){
//
//        Computer computer = new Computer("A","B",10);
//        Computer computer1 = new Computer("C","D",20);
//ComputerManager computerManager = new ComputerManager();
//computerManager.addComputer(computer);
//    }
private ComputerManager computerManager;
    private Computer computer;
    private static final Computer NULL_COMPUTER = null;

    private static final String COMPUTER_MANUFACTURER = "IGS";
    private static final String COMPUTER_MODEL = "KL-012";
    private static final double COMPUTER_PRICE = 100_000;

    private static final String NONE_EXISTING_MANUFACTURER = "MGB";
    private static final String NONE_EXISTING_MODEL = "AVOCADO-10";

    private static final int COMPUTER_INDEX = 0;
    private static final int COMPUTERS_COUNT = 1;

   @Before
    public void setUp() {
        computerManager = new ComputerManager();
        computer = new Computer(COMPUTER_MANUFACTURER, COMPUTER_MODEL, COMPUTER_PRICE);
        computerManager.addComputer(computer);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShouldThrowExceptionForTryingToModifyUnmodifiableCollection() {
        computerManager.getComputers().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionForTryingToAddComputerWithValueNull() {
        computerManager.addComputer(NULL_COMPUTER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionWhenTryingToAddAlreadyExistingComputer() {
        computerManager.addComputer(computer);
    }

    @Test
    public void testShouldRemoveComputerByGivenManufacturerAndModel() {
        Computer removedComputer = computerManager.removeComputer(COMPUTER_MANUFACTURER, COMPUTER_MODEL);
        Assert.assertEquals(computer, removedComputer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldTrowExceptionForNoneExistingManufacturerAndModelInComputerManger() {
        computerManager.getComputer(NONE_EXISTING_MANUFACTURER, NONE_EXISTING_MODEL);
    }

    @Test
    public void testShouldGetComputerByManufacturerAndModel() {
        Computer computerByManufacturerAndModel = computerManager.getComputer(COMPUTER_MANUFACTURER, COMPUTER_MODEL);
        Assert.assertEquals(computer, computerByManufacturerAndModel);
    }

    @Test
    public void testShouldGetComputerByManufacturerCorrectly() {
        List<Computer> computersByManufacturer = computerManager.getComputersByManufacturer(COMPUTER_MANUFACTURER);
        Assert.assertEquals(computer, computersByManufacturer.get(COMPUTER_INDEX));
    }

    @Test
    public void testShouldGetComputersCountFromTheComputerManager() {
        int computerManagerCount = computerManager.getCount();
        Assert.assertEquals(COMPUTERS_COUNT, computerManagerCount);
    }


}