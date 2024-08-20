package com.springboot.retotecnico.domain.exceptions;

public class StudentFoundException extends RuntimeException {

    public StudentFoundException(String details) {
        super(details);
    }
}
