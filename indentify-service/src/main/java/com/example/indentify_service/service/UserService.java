package com.example.indentify_service.service;


import com.example.indentify_service.dto.request.UserCreationRequest;
import com.example.indentify_service.dto.request.UserUpdateRequest;
import com.example.indentify_service.dto.response.UserResponse;
import com.example.indentify_service.entity.User;
import com.example.indentify_service.enums.Role;
import com.example.indentify_service.exception.AppException;
import com.example.indentify_service.exception.ErrorCode;
import com.example.indentify_service.mapper.UserMapper;
import com.example.indentify_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest request) {


        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10); bo dong nay di
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

        return userRepository.save(user);

    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        userMapper.updateUser(user, request);

//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());


        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers() {
//        return userRepository.findAll();
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse) // Map each User to UserResponse
                .collect(Collectors.toList());
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found")));
    }

//    public UserResponse getUser(String id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("user not found"));
//        System.out.println("Retrieved User: " + user); // Log the user object
//        return userMapper.toUserResponse(user);
//    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
