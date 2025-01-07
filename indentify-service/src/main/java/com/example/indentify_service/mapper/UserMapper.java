package com.example.indentify_service.mapper;



//import com.example.indentify_service.dto.request.UserCreationRequest;
import com.example.indentify_service.dto.request.UserCreationRequest;
import com.example.indentify_service.dto.request.UserUpdateRequest;
import com.example.indentify_service.dto.response.UserResponse;
import com.example.indentify_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

        User toUser(UserCreationRequest request);

        @Mapping(source = "id" , target = "id")
//        @Mapping(source = "firstName", target = "firstName")
//        @Mapping(source = "lastName", target = "lastName")
//        @Mapping(source = "dob", target = "dob")
        UserResponse toUserResponse(User user);

        void updateUser(@MappingTarget User user , UserUpdateRequest request);

}
