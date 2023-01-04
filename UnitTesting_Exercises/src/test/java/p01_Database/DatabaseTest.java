package p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    private final Integer[] ELEMENTS = {15, 4, 200, 17};

    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(ELEMENTS);
    }

    @Test
    public void testDatabaseGetElementsShouldReturnCorrectElements() {
        assertArrayEquals(ELEMENTS, this.database.getElements());
    }

    @Test
    public void testDatabaseConstructorShouldCrateObjectWithSixteenElements() throws OperationNotSupportedException {
        int size = 16;

        Integer[] elements = new Integer[size];

        new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseConstructorShouldThrowWhenCalledWithMoreThanSixteenElements() throws OperationNotSupportedException {
        int size = 17;

        Integer[] elements = new Integer[size];

        new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseConstructorShouldThrowWhenCalledWithZeroElements() throws OperationNotSupportedException {
        int size = 0;

        Integer[] elements = new Integer[size];

        new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowWhenParameterIsNull() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void testAddShouldAddAtFirstFreeIndex() throws OperationNotSupportedException {
        int size = 3;

        Integer[] expectedElements = new Integer[size];
        for (int i = 0; i < size; i++) {
            database.add(i);
            expectedElements[i] = i;
        }

        Integer[] addedElements = new Integer[size];

        Integer[] databaseElements = this.database.getElements();
        System.arraycopy(databaseElements, databaseElements.length - size,
                addedElements, 0, size);

        assertArrayEquals(expectedElements, addedElements);
    }

    @Test
    public void testRemoveShouldRemoveLastElement() throws OperationNotSupportedException {
        Integer[] databaseElements = this.database.getElements();

        for (int i = ELEMENTS.length - 1; i >= 0; i--) {
            Integer lastElement = databaseElements[this.database.getElements().length - 1];

            this.database.remove();

            assertEquals(ELEMENTS[i], lastElement);
        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowWithEmptyDatabase() throws OperationNotSupportedException {
        for (int i = 0; i < ELEMENTS.length; i++) {
            this.database.remove();
        }
        this.database.remove();
    }
}