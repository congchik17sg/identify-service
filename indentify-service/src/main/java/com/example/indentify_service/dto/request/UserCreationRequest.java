package com.example.indentify_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

//@Getter
//@Setter
@Data // = getter va setter....
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 2, message = "USERNAME_UNVALID")
    String username;
    @Size(min = 8, message = "PASSWORD_UNVALID")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;


}
