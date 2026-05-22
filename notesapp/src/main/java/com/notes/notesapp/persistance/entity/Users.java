package com.notes.notesapp.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
private String firstName;
private String lastName;
@Column(unique = true)
    private String email;
private String password;
private LocalDateTime createdAt;
@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user",orphanRemoval = true)
  private List<Notes> notes;
}
