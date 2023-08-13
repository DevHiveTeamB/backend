package com.devhive03.Controller.ExceptionControll;

public class FileIsNotIOException extends RuntimeException {
    public FileIsNotIOException(String message) {
        super(message);
    }
}
