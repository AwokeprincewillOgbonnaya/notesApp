package com.notes.notesapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.notes.notesapp.persistance.entity.Users;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;

public static UserResponse fromEntity(Users user){
UserResponse response = new UserResponse();
response.setFirstname(user.getFirstName());
response.setLastName(user.getLastName());
response.setEmail(user.getEmail());
response.setCreatedAt(user.getCreatedAt());

return response;
}

}
