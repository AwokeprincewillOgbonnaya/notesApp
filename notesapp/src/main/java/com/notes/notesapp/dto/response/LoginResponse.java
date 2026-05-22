package com.notes.notesapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.notes.notesapp.persistance.entity.Users;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private Long id;
    private String firstname;
    private String lastName;
    private String email;

    public static LoginResponse fromEntity(Users user){

        LoginResponse response = new LoginResponse();
        response.setId(user.getId());
        response.setFirstname(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());

        return response;
    }
}
