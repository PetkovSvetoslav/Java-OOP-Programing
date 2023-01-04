package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomLinkedListTest {

    private CustomLinkedList<String> linkedList;

    @Before
    public void setUp() {
        this.linkedList = new CustomLinkedList<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowWhenTheIndexIsOutOfBounds() {
        this.linkedList.get(0);
    }

    @Test
    public void testAddInEmptyList() {
        String testString = "Test";
        this.linkedList.add(testString);
        assertEquals(testString, this.linkedList.get(0));
    }

    @Test
    public void testAddShouldAddCorrectElements() {
        int count = 4;
        List<String> addedElements = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String testString = "TestNo_" + (i + 1);
            addedElements.add(testString);
            this.linkedList.add(testString);
        }

        for (int i = 0; i < count; i++) {
            assertEquals(addedElements.get(i), linkedList.get(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowWhenTheIndexIsOutOfBounds() {
        this.linkedList.set(0, "Element");
    }

    @Test
    public void testSetShouldSetCorrect() {
        this.linkedList.add("Before");

        String newString = "After";
        this.linkedList.set(0, newString);

        assertEquals(newString, this.linkedList.get(0));
    }

    @Test
    public void testRemoveShouldReturnMinusOneWhenTheElementDoesntContain() {
        this.linkedList.add("Wizard");

        int result = this.linkedList.remove("InvalidElement");

        assertEquals(-1, result);
    }

    @Test
    public void testRemoveShouldRemoveTheItem() {
        int size = 3;
        List<String> elements = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String testString = "TestNo_" + (i + 1);
            this.linkedList.add(testString);
            elements.add(testString);
        }

        String removedString = "TestNo_2";
        this.linkedList.remove(removedString);

        assertFalse(this.linkedList.contains(removedString));
    }

    @Test
    public void testRemoveShouldReturnTheIndexOfTheRemovedItem() {
        int size = 3;

        for (int i = 0; i < size; i++) {
            this.linkedList.add("TestNo_" + (i + 1));
        }

        int expected = 1;

        int result = this.linkedList.remove("TestNo_2");
        assertEquals(expected, result);
    }

    @Test
    public void testContainsWhenThereIsNoSuchItem() {
        this.linkedList.add("ABC");
        assertFalse(this.linkedList.contains("A"));
    }

    @Test
    public void testContainsWhenThereIsSuchAnItem() {
        this.linkedList.add("Peter");
        this.linkedList.add("George");
        this.linkedList.add("Ivan");

        assertTrue(this.linkedList.contains("George"));
    }
}