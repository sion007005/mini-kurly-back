package com.sion.minikurlyback.utils;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(Throwable e) {
        super(e);
    }

    public DuplicateMemberException(String message) {
        super(message);
    }

    public DuplicateMemberException(String message, Throwable e) {
        super(message, e);
    }
}