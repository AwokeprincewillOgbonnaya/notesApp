package com.notes.notesapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.notes.notesapp.persistance.entity.Notes;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotesResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

public static NotesResponse fromEntity(Notes note){
   NotesResponse response = new NotesResponse();
   response.setId(note.getId());
   response.setTitle(note.getTitle());
   response.setContent(note.getContent());
   response.setCreatedAt(note.getCreatedAt());
   response.setUpdatedAt(note.getUpdatedAt());
   response.setUserId(note.getUser() != null ? note.getUser().getId() : null);
   return response;
}

}
