package com.notes.notesapp.persistance.repository;

import com.notes.notesapp.persistance.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Notes,Long> {
List<Notes> findByTitle(String title);
List<Notes>findByUserId(Long id);
List<Notes> findByTitleContaining(String title);
}
