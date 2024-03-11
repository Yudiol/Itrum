package com.yudiol.springbootjsonview.exception.errors;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message) {
        super(message);
    }
}
