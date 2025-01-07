package com.example.indentify_service.controller;


import com.example.indentify_service.dto.response.ApiResponse;
import com.example.indentify_service.dto.request.UserCreationRequest;
import com.example.indentify_service.dto.request.UserUpdateRequest;
import com.example.indentify_service.dto.response.UserResponse;
import com.example.indentify_service.entity.User;
import com.example.indentify_service.service.UserService;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));
        apiResponse.setMessage(apiResponse.getMessage());
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info("Authority: {}", grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "user has been deleted";
    }
}
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public User createUser(@RequestBody UserCreationRequest request) {
//        return userService.createUser(request);
//    }
//}