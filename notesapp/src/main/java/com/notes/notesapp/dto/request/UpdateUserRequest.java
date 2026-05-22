package com.notes.notesapp.dto.request;

public record UpdateUserRequest(
String firstName,
String lastname,
String email
) {
}
