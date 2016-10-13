package com.github.albertosh.adidasevents.sdk.api;

public class NetworkOperationError {

    private int code;
    private String text;

    public NetworkOperationError(int code) {
        this(code, "");
    }

    public NetworkOperationError(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getCode() {
        return code;
    }


}
