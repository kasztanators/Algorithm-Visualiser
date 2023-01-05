package com.company.algorithms.test;

import static org.junit.Assert.assertArrayEquals;

import com.company.algorithms.InsertionSort;
import org.junit.Before;
import org.junit.Test;

public class InsertionSortTest {
    private InsertionSort insertionSort;

    @Before
    public void setUp() {
        insertionSort = new InsertionSort();
        insertionSort.setSpeed(0);
        insertionSort.setAnimationSpeed(0);
    }
    @Test
    public void testSortReversed() {
        int[] data = {5, 4, 3, 2, 1};
        insertionSort.setData(data);
        insertionSort.sort();
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, insertionSort.getData());
    }
    @Test
    public void testAlreadySortedData(){
        int[] data = {1, 2, 3, 4, 5};
        insertionSort.setData(data);
        insertionSort.sort();

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, insertionSort.getData());
    }

}