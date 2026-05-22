package com.notes.notesapp.service;

import com.notes.notesapp.dto.request.CreateNoteRequest;
import com.notes.notesapp.dto.request.UpdateNoteRequest;
import com.notes.notesapp.dto.response.GenericResponse;
import com.notes.notesapp.dto.response.NotesResponse;
import com.notes.notesapp.exception.NotesAppException;
import com.notes.notesapp.persistance.entity.Notes;
import com.notes.notesapp.persistance.entity.Users;
import com.notes.notesapp.persistance.repository.NoteRepository;
import com.notes.notesapp.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {
  private final UserRepository userRepository;
  private final NoteRepository noteRepository;

public GenericResponse<NotesResponse> createNote(Long userId, CreateNoteRequest request){
    Users users = userRepository.findById(userId).orElseThrow(()-> new NotesAppException("User not found",404));
    Notes notes = new Notes();
    notes.setTitle(request.title());
    notes.setContent(request.content());
    notes.setCreatedAt(LocalDateTime.now());
    notes.setUser(users);
    noteRepository.save(notes);
   return new GenericResponse<>("Note Created Successfully", NotesResponse.fromEntity(notes),200);
}
public GenericResponse<NotesResponse> updateNote(Long id,UpdateNoteRequest request){
   Notes notes = findNotesById(id);
    notes.setTitle(request.title());
    notes.setContent(request.content());
    notes.setUpdatedAt(LocalDateTime.now());
    noteRepository.save(notes);
    return new GenericResponse<>("Note Updated Successfully",NotesResponse.fromEntity(notes),200);
}

public GenericResponse<NotesResponse> deleteNote(Long id){
    Notes notes = findNotesById(id);
    noteRepository.delete(notes);
    return new GenericResponse<>("Notes deleted Successfully", 200);
}

public GenericResponse<NotesResponse> getOneNote(Long id){
    Notes notes = findNotesById(id);
return new GenericResponse<>("Notes Retrieved Successfully",NotesResponse.fromEntity(notes),200);
}

public GenericResponse<List<NotesResponse>> getAllNotes(Long userId){
Users users = userRepository.findById(userId).orElseThrow(()-> new NotesAppException("User not found",404));
List<Notes> notes = noteRepository.findByUserId(userId);
List<NotesResponse> responses = notes.stream().map(NotesResponse::fromEntity).toList();
    return new GenericResponse<>("Notes retrieved successfully", responses, 200);
}

public GenericResponse<List<NotesResponse>> searchNoteByTitle(String title){
    List<Notes> notes = noteRepository.findByTitleContaining(title);
    List<NotesResponse> responses = notes.stream().map(NotesResponse::fromEntity).toList();
    return new GenericResponse<>("Note retrieved successfully",responses,200);
}

    private Notes findNotesById(Long id){
        return noteRepository.findById(id).orElseThrow(()-> new NotesAppException("Notes id: " +id +" not found",404));
    }
}
