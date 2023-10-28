package com.spring.security.model;

import lombok.*;

@Data
@Setter
@Getter
@Builder
public class AuthenticationResponse {
    private String authenticationToken;
}
