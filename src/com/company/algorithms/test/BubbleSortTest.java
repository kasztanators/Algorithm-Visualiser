package com.company.algorithms.test;

import static org.junit.Assert.assertArrayEquals;

import com.company.algorithms.BubbleSort;
import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {
    private BubbleSort bubbleSort;

    @Before
    public void setUp(){
        bubbleSort = new BubbleSort();
        bubbleSort.setAnimationSpeed(0);
        bubbleSort.setSpeed(0);
    }

    @Test
    public void testSortReversed() {
        int[] data = {5, 4, 3, 2, 1};
        bubbleSort.setData(data);
        bubbleSort.sort();
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, bubbleSort.getData());
    }
    @Test
    public void testAlreadySortedData(){
        int[] data = {1, 2, 3, 4, 5};
        bubbleSort.setData(data);
        bubbleSort.sort();

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, bubbleSort.getData());
    }


}