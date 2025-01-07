package com.example.indentify_service.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data // = getter va setter....
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectResponse {

      boolean valid;

}
