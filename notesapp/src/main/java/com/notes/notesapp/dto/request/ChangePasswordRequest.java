package com.notes.notesapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank(message = "Input current password") String currentPassword,
        @NotBlank(message = "Input new password")
        @Size(min = 8,message = "Password must be at least 8 characters") String newPassword
) {
}
