package com.design.logic;

public class Logic {
    public interface ActivityProvider {
        <T extends Throwable> T findViewById(int id);

        int getResources();

        void getSupportFragmentManager();

        String getString(int resId);
    }

    private ActivityProvider activityProvider;

    public Logic(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
        init();
    }

    private void init() {
        activityProvider.getSupportFragmentManager();
    }


    public static void main(String[] args) {
        Child child = new Child();
    }
}
