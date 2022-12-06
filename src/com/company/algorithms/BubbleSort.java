package com.company.algorithms;

public class BubbleSort {
    private int [] input;

    public BubbleSort(int [] input){
        this.input = input.clone();
    }
    private void swap(int i, int j){
        int tmp = this.input[i];
        this.input[i]= this.input[j];
        this.input[j] = tmp;
    }
    public int [] sort(){
        if (this.input == null)
            return null;
        for(int i = 0; i <this.input.length; i++){
            for(int j =i +1; j <this.input.length; j++){
                if (this.input[j]<this.input[i]){
                    swap(i,j);
                }
            }
        }
        return this.input;
    }

}
