package com.pdcmyanmar.helloworld.events;

public class ApiErrorEvent {

    private String errorMsg;

    public ApiErrorEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
