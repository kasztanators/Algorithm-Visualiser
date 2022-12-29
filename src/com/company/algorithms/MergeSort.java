package com.company.algorithms;

import javax.swing.*;

public class MergeSort extends JPanel {
    private int [] input;
    public MergeSort(int [] input) {
        this.input = input;
    }
    private int [] merge(int left [], int right[]){ // sort items in two arrays in a single array (merge two arrays in a sorted array)
        int [] ret = new int [right.length + left.length];
        int j = 0;
        int rightIndex = 0;
        int leftIndex = 0;
        while (j < ret.length){
            if (rightIndex < right.length && leftIndex < left.length){
                if (right[rightIndex] < left[leftIndex]){
                    ret[j] = right[rightIndex];
                    rightIndex++;
                }else {
                    ret[j] = left[leftIndex];
                    leftIndex++;
                }
            }else if (rightIndex < right.length){
                ret[j] = right[rightIndex];
                rightIndex++;
            }else {
                ret[j] = left[leftIndex];
                leftIndex++;
            }
            j++;
        }
        return ret;
    }

    public  int [] sort(){
        if (this.input == null)
            return null;
        return mergeSort(this.input, this.input.length);
    }

    private int [] mergeSort(int [] input, int p){
        if (input.length == 1){ // return if the array has only one item
            return input;
        }

        return merge( // merge left part and right part
                //left part
                mergeSort(copyOfRange(input, 0, p/2), p/2),
                //right part
                mergeSort(copyOfRange(input, p/2, input.length), input.length - p/2)
        );
    }
    private int [] copyOfRange(int [] input, int i, int j){ // copy a range of an array into a new array
        int range [] = new int [j-i];
        for (int k = i; k < j; k++) {
            range [k-i] = input[k];
        }
        return range;
    }
}
