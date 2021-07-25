package com.data;

public class Main {
    public void getLocation(int []location){
        location[0]=5;
        location[1]=4;
    }

    public static void main(String[] args) {
	// write your code here
        int[] arr = new int[2];
        new Main().getLocation(arr);
        System.out.println(arr[0]+" "+arr[1]);
    }
}
