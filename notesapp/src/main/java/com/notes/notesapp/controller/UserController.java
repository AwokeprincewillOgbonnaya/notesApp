package com.notes.notesapp.controller;

import com.notes.notesapp.dto.request.*;
import com.notes.notesapp.dto.response.GenericResponse;
import com.notes.notesapp.dto.response.LoginResponse;
import com.notes.notesapp.dto.response.UserResponse;
import com.notes.notesapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;

@PostMapping("/create")
    public ResponseEntity<GenericResponse<UserResponse>> createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
    return  ResponseEntity.ok(userService.createUser(createUserRequest));
}

@PutMapping("/update/{id}")
public ResponseEntity<GenericResponse<UserResponse>> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserRequest updateUserRequest){
return ResponseEntity.ok(userService.updateUser(id, updateUserRequest));
}

@GetMapping("/retrieve/{id}")
public ResponseEntity<Optional<GenericResponse<UserResponse>>> getUserById(@PathVariable("id") Long id){
return ResponseEntity.ok(userService.getUserById(id));
}

@DeleteMapping("/delete/{id}")
 public ResponseEntity<GenericResponse<?>> deleteUser(@PathVariable("id") Long id){
return ResponseEntity.ok(userService.deleteUser(id));
}

@PatchMapping("/change-email/{id}")
    public ResponseEntity<GenericResponse<UserResponse>> changeUserEmail(@PathVariable("id") Long id, @RequestBody @Valid ChangeEmailRequest changeEmailRequest){
return ResponseEntity.ok(userService.changeUserEmail(id, changeEmailRequest));
}

@PatchMapping("/change-password/{id}")
    public ResponseEntity<GenericResponse<UserResponse>> changeUserPassword(@PathVariable("id") Long id, @RequestBody @Valid ChangePasswordRequest changePasswordRequest){
    return ResponseEntity.ok(userService.changeUserPassword(id, changePasswordRequest));
}

@PostMapping("/login")
    public ResponseEntity<GenericResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest loginRequest){
    return ResponseEntity.ok(userService.userLogin(loginRequest));
}
}
