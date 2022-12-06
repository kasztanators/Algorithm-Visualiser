package com.company.algorithms;

public class InsertionSort {
    private int [] input;
    public InsertionSort(int [] input) {
        this.input = input.clone();
    }
    public int [] sort(){
        // a loop for finding a small item and then swapping it with other bigger items
        for (int i = 1; i < this.input.length; i++) {
            int j = i;
            while (j > 0 && this.input[j] < this.input[j - 1]){
                //swap item j-1 and j
                swap(j - 1,j);
                j--;
            }
        }
        return this.input;
    }
    private void swap(int i, int j){ // swap item i and j in the array
        int tmp = this.input[i];
        this.input[i] = this.input[j];
        this.input[j] = tmp;
    }
}
