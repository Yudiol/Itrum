package com.yudiol.securitysecond.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class UserController {

    @GetMapping("/public")
    public String getPublic() {
        return "public";
    }

    @GetMapping("/user/**")
    public String getUser() {
        return "user";
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/moderator")
    public String getModerator() {
        return "moderator";
    }
}
