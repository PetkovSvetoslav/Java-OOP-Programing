package p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {
    private static final String[] ELEMENTS = {
            "The",
            "Quick",
            "Brown",
            "Fox"
    };

    private ListIterator listIterator;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.listIterator = new ListIterator(ELEMENTS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testListIteratorConstructorShouldThrowWithNullAsParameter() throws OperationNotSupportedException {
        new ListIterator((String[]) null);
    }

    @Test
    public void testMovePassingAllElements() {
        int numberOfMovements = 0;

        while (this.listIterator.move()) {
            numberOfMovements++;
        }

        assertEquals(ELEMENTS.length - 1, numberOfMovements);
    }

    @Test
    public void testHasNextShouldReturnCorrectBoolean() throws OperationNotSupportedException {
        assertTrue(this.listIterator.hasNext());

        //Empty ListIterator
        this.listIterator = new ListIterator();
        assertFalse(this.listIterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintShouldThrowWhenListIteratorIsEmpty() throws OperationNotSupportedException {
        this.listIterator = new ListIterator();
        this.listIterator.print();
    }

    @Test
    public void testPrintShouldReturnCorrectElements() {
        int i = 0;
        while (i < ELEMENTS.length || this.listIterator.hasNext()) {
            assertEquals(ELEMENTS[i++], this.listIterator.print());
            listIterator.move();
        }
    }
}