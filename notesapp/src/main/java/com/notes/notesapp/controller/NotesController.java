package com.notes.notesapp.controller;

import com.notes.notesapp.dto.request.CreateNoteRequest;
import com.notes.notesapp.dto.request.UpdateNoteRequest;
import com.notes.notesapp.dto.response.GenericResponse;
import com.notes.notesapp.dto.response.NotesResponse;
import com.notes.notesapp.service.NotesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;

@PostMapping("/create/{id}")
    public ResponseEntity<GenericResponse<NotesResponse>> createNote(@PathVariable("id") Long userId, @RequestBody @Valid CreateNoteRequest request){
return ResponseEntity.ok(notesService.createNote(userId,request));
}

@PutMapping("/update/{id}")
    public ResponseEntity<GenericResponse<NotesResponse>> updateNotes(@PathVariable("id") Long id, @RequestBody @Valid UpdateNoteRequest request){
    return ResponseEntity.ok(notesService.updateNote(id, request));
}

@DeleteMapping("delete/{id}")
    public ResponseEntity<GenericResponse<?>>  deleteNotes(@PathVariable("id") Long id){
    return ResponseEntity.ok(notesService.deleteNote(id));
}

@GetMapping("/retrieve/{id}")
    public ResponseEntity<GenericResponse<NotesResponse>> getNote(@PathVariable("id") Long id){
    return ResponseEntity.ok(notesService.getOneNote(id));
}

@GetMapping("/retrieve-all/{id}")
    public ResponseEntity<GenericResponse<List<NotesResponse>>> getAllNotes(@PathVariable("id") Long id){
return ResponseEntity.ok(notesService.getAllNotes(id));
}

@GetMapping("/search")
    public ResponseEntity<GenericResponse<List<NotesResponse>>> getNoteByTitle(@RequestParam String title){
    return ResponseEntity.ok(notesService.searchNoteByTitle(title));
}
}
