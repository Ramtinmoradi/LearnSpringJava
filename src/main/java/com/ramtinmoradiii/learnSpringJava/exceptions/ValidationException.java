package com.ramtinmoradiii.learnSpringJava.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
