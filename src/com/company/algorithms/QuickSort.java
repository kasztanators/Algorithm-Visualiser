package com.company.algorithms;

public class QuickSort {
    private int [] input;


    private void quickSort(int left, int right){ // put pivot in the right place and 
        //do the same for the left part and right part
        if (left < right){
            int pivot = partition(left, right);
            quickSort(left, pivot - 1);
            quickSort(pivot + 1, right);
        }
    }
    private int partition(int left, int right){ // select a pivot index, put 
        //items less than the pivot value before the pivot index, and put items 
        //greater than pivot value after the pivot index
        int pivot =  ((int) (Math.random() * (right - left)) + left);
        int pivot_value = this.input[pivot];
        swap(pivot, right);
        int lastIndex = left;
        for (int i = left; i < right; i++) {
            if (this.input[i] < pivot_value){
                swap(lastIndex, i);
                lastIndex++;
            }
        }
        swap(lastIndex, right);
        return lastIndex;
    }
    private void swap(int i, int j){ // swap item i and j in the array
        int tmp = this.input[i];
        this.input[i] = this.input[j];
        this.input[j] = tmp;
    }

    public int [] sort(){
        quickSort(0, this.input.length - 1);
        return this.input;
    }
}
