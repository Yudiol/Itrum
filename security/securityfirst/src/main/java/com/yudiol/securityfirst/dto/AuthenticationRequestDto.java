package com.yudiol.securityfirst.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
