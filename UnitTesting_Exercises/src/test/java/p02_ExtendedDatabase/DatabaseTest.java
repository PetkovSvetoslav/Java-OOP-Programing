package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    private final Person[] PEOPLE = {
            new Person(1, "First"),
            new Person(2, "Second"),
            new Person(3, "Third"),
            new Person(4, "Fourth"),
    };

    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(PEOPLE);
    }

    @Test
    public void testDatabaseGetElementsShouldReturnCorrectElements() {
        assertArrayEquals(PEOPLE, this.database.getElements());
    }

    @Test
    public void testDatabaseConstructorShouldCrateObjectWithSixteenElements() throws OperationNotSupportedException {
        int size = 16;

        Person[] people = new Person[size];

        new Database(people);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseConstructorShouldThrowWhenCalledWithMoreThanSixteenElements() throws OperationNotSupportedException {
        int size = 17;

        Person[] people = new Person[size];

        new Database(people);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseConstructorShouldThrowWhenCalledWithZeroElements() throws OperationNotSupportedException {
        int size = 0;

        Person[] people = new Person[size];

        new Database(people);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowWhenParameterIsNull() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void testAddShouldAddAtFirstFreeIndex() throws OperationNotSupportedException {
        int size = 3;

        Person[] expectedPeople = new Person[size];
        for (int i = 0; i < size; i++) {
            Person testPerson = new Person(i, "TestPersonNo_" + i);
            database.add(testPerson);
            expectedPeople[i] = testPerson;
        }

        Person[] addedPeople = new Person[size];

        Person[] databasePeople = this.database.getElements();
        System.arraycopy(databasePeople, databasePeople.length - size,
                addedPeople, 0, size);

        assertArrayEquals(expectedPeople, addedPeople);
    }

    @Test
    public void testRemoveShouldRemoveLastElement() throws OperationNotSupportedException {
        Person[] databasePeople = this.database.getElements();

        for (int i = PEOPLE.length - 1; i >= 0; i--) {
            Person lastPerson = databasePeople[this.database.getElements().length - 1];

            this.database.remove();

            assertEquals(PEOPLE[i], lastPerson);
        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowWithEmptyDatabase() throws OperationNotSupportedException {
        for (int i = 0; i < PEOPLE.length; i++) {
            this.database.remove();
        }
        this.database.remove();
    }

    @Test
    public void testFindByUsernameShouldReturnTheCorrectPerson() throws OperationNotSupportedException {
        Person found = this.database.findByUsername("First");
        assertEquals(PEOPLE[0], found);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowWhenSearchingByNull() throws OperationNotSupportedException {
        this.database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowWhenThereIsMoreThanOneWithTheSameUsername() throws OperationNotSupportedException {
        final String RECURRING_USERNAME = "Angel";

        Person[] people = {
                new Person(1, RECURRING_USERNAME),
                new Person(11, "Peter"),
                new Person(111, RECURRING_USERNAME),
        };
        Database database;

        try {
            database = new Database(people);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException("Invalid Database construction!");
        }

        database.findByUsername(RECURRING_USERNAME);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowWhenThereIsNoMatch() throws OperationNotSupportedException {
        String username = "NoMatch";

        this.database.findByUsername(username);
    }

    @Test
    public void testFindByIdShouldReturnTheCorrectPerson() throws OperationNotSupportedException {
        Person found = this.database.findById(1);
        assertEquals(PEOPLE[0], found);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdShouldThrowWhenThereIsMoreThanOneWithTheSameId() throws OperationNotSupportedException {
        final int REDUCING_ID = 97345;

        Person[] people = {
                new Person(REDUCING_ID, "Ivan"),
                new Person(REDUCING_ID, "Peter"),
                new Person(1, "Georgi"),
        };

        Database database;

        try {
            database = new Database(people);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException("Invalid Database construction!");
        }

        database.findById(REDUCING_ID);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdShouldThrowWhenThereIsNoMatch() throws OperationNotSupportedException {
        this.database.findById(0);
    }
}