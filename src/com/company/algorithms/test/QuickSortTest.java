package com.company.algorithms.test;

import static org.junit.Assert.assertArrayEquals;

import com.company.algorithms.QuickSort;
import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {
    private QuickSort quickSort;

    @Before
    public void setUp() {
        quickSort = new QuickSort();
        quickSort.setSpeed(0);
        quickSort.setAnimationSpeed(0);
    }

    @Test
    public void testSortReversed() {
        int[] data = {5, 4, 3, 2, 1};
        quickSort.setData(data);
        quickSort.sort();
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, quickSort.getData());
    }
    @Test
    public void testAlreadySortedData(){
        int[] data = {1, 2, 3, 4, 5};
        quickSort.setData(data);
        quickSort.sort();

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, quickSort.getData());
    }

}