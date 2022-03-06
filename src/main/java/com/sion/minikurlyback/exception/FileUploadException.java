package com.sion.minikurlyback.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException(Throwable e) {
        super(e);
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable e) {
        super(message, e);
    }
}