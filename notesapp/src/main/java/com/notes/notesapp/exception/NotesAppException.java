package com.notes.notesapp.exception;

public class NotesAppException extends RuntimeException {
    private final int statusCode;

    public NotesAppException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
