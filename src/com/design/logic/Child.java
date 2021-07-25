package com.design.logic;

public class Child extends Parent implements Logic.ActivityProvider {
    public Child() {
        Logic logic = new Logic(this);
    }
}
