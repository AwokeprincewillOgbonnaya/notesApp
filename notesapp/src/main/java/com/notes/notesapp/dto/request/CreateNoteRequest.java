package com.notes.notesapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateNoteRequest(
@NotBlank(message = "Title is required") @Size(max = 100,message = "Title cannot exceed 100 character") String title,
@NotBlank(message = "Content is required") String content
) {
}
