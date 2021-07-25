package com.design.logic;


public class Parent {
    public<T extends Throwable>  T findViewById(int id){
        return (T) new Exception("parent");
    }
    public int getResources(){return 0;}
    public void getSupportFragmentManager(){
        System.out.println("parent fragment");
    }
    public String getString(int resId){return resId+"parent";}
}
