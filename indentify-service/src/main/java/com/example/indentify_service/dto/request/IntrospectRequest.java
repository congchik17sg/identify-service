package com.example.indentify_service.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data // = getter va setter....
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectRequest {

   String token ;
}
