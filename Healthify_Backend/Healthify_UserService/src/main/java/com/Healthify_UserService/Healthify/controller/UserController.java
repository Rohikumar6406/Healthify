package com.Healthify_UserService.Healthify.controller;

import com.Healthify_UserService.Healthify.dto.RegisterRequest;
import com.Healthify_UserService.Healthify.dto.UserResponse;
import com.Healthify_UserService.Healthify.services.Userservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final Userservice userservice;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(userservice.register(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(userservice.getUserProfile(userId));

    }
    //To validate the webclient id
    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId){
        return ResponseEntity.ok(userservice.existByUserId(userId));

    }

}