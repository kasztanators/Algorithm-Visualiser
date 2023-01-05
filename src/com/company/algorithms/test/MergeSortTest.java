package com.company.algorithms.test;

import static org.junit.Assert.assertArrayEquals;

import com.company.algorithms.MergeSort;
import org.junit.Before;
import org.junit.Test;

public class MergeSortTest {
    private MergeSort mergeSort;

    @Before
    public void setUp() {
        mergeSort = new MergeSort();
        mergeSort.setSpeed(0);
        mergeSort.setAnimationSpeed(0);
    }
    @Test
    public void testSortReversed() {
        int[] data = {5, 4, 3, 2, 1};
        mergeSort.setData(data);
        mergeSort.sort();
        int[] expected = {1, 2, 3, 4, 5};

        assertArrayEquals(expected, mergeSort.getData());
    }
    @Test
    public void testAlreadySortedData(){
        int[] data = {1, 2, 3, 4, 5};
        mergeSort.setData(data);
        mergeSort.sort();

        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, mergeSort.getData());
    }

}
