package com.yudiol.springbootjsonview.exception.errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String objectName, String objectId) {
        super(objectName + " c id " + objectId + " не найден.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
