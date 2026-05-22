package com.notes.notesapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
  @NotBlank(message = "Email is required") @Email(message = "Enter valid email") String email,
  @NotBlank(message = "Password required")  String password
)
{ }
