package p04_BubbleSortTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BubbleTest {
    private int[] jumbledArr;
    private int[] sortedArr;

    @Before
    public void setUp() {
        this.jumbledArr = new int[]{11, -21, 904, 0, 44, 3};
        this.sortedArr = new int[]{-21, 0, 3, 11, 44, 904};
    }

    @Test
    public void testSortWithZeroElements() {
        int[] arr = new int[0];
        Bubble.sort(arr);
    }

    @Test
    public void testSortWithJumbledElements() {
        Bubble.sort(this.jumbledArr);
        assertArrayEquals(this.sortedArr, this.jumbledArr);
    }
}