package com.invariant.saleserver.service.action;

public enum ResultStatus {

    SUCCESS("success"),
    ERROR("error");

    private final String name;

    public String getName(){
        return name;
    }

    ResultStatus(String name) {
        this.name = name;
    }
}
