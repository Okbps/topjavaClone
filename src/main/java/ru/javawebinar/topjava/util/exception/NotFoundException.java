package ru.javawebinar.topjava.util.exception;

/**
 * Created by Aspire on 10.12.2016.
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
