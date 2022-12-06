package com.company;

import com.company.algorithms.BubbleSort;
import com.company.algorithms.QuickSort;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[50];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);
        }
        BubbleSort bubbleSort = new BubbleSort(arr);
        String sorted = Arrays.toString(bubbleSort.sort());
        System.out.println(sorted);


    }
}
