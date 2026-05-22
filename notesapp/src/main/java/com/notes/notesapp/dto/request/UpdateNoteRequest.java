package com.notes.notesapp.dto.request;

public record UpdateNoteRequest(
        String title,
        String content
) {
}
