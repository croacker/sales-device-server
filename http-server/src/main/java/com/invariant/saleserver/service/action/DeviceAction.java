package com.invariant.saleserver.service.action;

public enum  DeviceAction {

    PRINT_CHECK("PRINT_CHECK");

    private final String name;

    public String getName(){
        return name;
    }

    DeviceAction(String name) {
        this.name = name;
    }
}
