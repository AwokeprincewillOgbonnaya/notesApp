package com.notes.notesapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ChangeEmailRequest(
        @NotBlank(message = "Enter old email")@Email(message = "Enter a valid email") String oldEmail,
        @NotBlank(message = "Enter new email")@Email(message = "Enter a valid email") String newEmail
) {
}
