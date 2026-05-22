package com.notes.notesapp.service;

import com.notes.notesapp.dto.request.*;
import com.notes.notesapp.dto.response.GenericResponse;
import com.notes.notesapp.dto.response.LoginResponse;
import com.notes.notesapp.dto.response.UserResponse;
import com.notes.notesapp.exception.NotesAppException;
import com.notes.notesapp.persistance.entity.Users;
import com.notes.notesapp.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public GenericResponse<UserResponse> createUser (CreateUserRequest request){
        validateUserEmail(request.email());
Users user = new Users();
user.setFirstName(request.firstName());
user.setLastName(request.lastName());
user.setEmail(request.email());
user.setPassword(request.password());
user.setCreatedAt(LocalDateTime.now());
userRepository.save(user);
return new GenericResponse<>("User Created Successfully",UserResponse.fromEntity(user),200);
    }

    public GenericResponse<UserResponse> updateUser(Long id,UpdateUserRequest request){
Users user = findUserById(id);
user.setFirstName(request.firstName());
user.setLastName(request.lastname());
userRepository.save(user);
return new GenericResponse<>("User Updated Successfully",UserResponse.fromEntity(user),200);
    }
 public Optional<GenericResponse<UserResponse>> getUserById(Long userId){
        Users users = findUserById(userId);
        return Optional.of(new GenericResponse<>("User retrieved successfully",UserResponse.fromEntity(users),200));
 }

    public GenericResponse<UserResponse> changeUserEmail(Long id, ChangeEmailRequest request){
        Users user = findUserById(id);
        user.setEmail(request.newEmail());
        return new GenericResponse<>("Email Changed Successfully",UserResponse.fromEntity(user),200);
    }

    public GenericResponse<UserResponse> changeUserPassword(Long id, ChangePasswordRequest request){
        Users user = findUserById(id);
        if (!user.getPassword().equals(request.currentPassword())){
            throw new NotesAppException("Old password is incorrect",200);
        }
        user.setPassword(request.newPassword());
        userRepository.save(user);
        return new GenericResponse<>("Password Changed Successfully",UserResponse.fromEntity(user),200);
    }

    public GenericResponse<UserResponse> deleteUser (Long id){
        Users user = findUserById(id);
        userRepository.delete(user);

        return new GenericResponse<>("User Deleted Successfully",200);
    }

    public GenericResponse<LoginResponse> userLogin(LoginRequest request){
        Users users = userRepository.findByEmail(request.email()).orElseThrow(()-> new NotesAppException("Invalid email or password",401));
        if (!users.getPassword().equals(request.password())){throw new NotesAppException("Invalid email or password",401);
        }
        return new GenericResponse<>("Login Successful", LoginResponse.fromEntity(users),200);
    }

    private Users findUserById(Long id){
      return userRepository.findById(id).orElseThrow(() -> new NotesAppException("User with id " + id + " not found", 404));
    }

    private void validateUserEmail(String email) {
        Optional<Users> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new NotesAppException("Email Already exist :" + email, 400);
        }
    }
}
