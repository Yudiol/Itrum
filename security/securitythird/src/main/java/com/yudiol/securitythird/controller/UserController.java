package com.yudiol.securitythird.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users")
    public String getUser() {
        return "USER";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdmin() {
        return "ADMIN";
    }

    @GetMapping("/public")
    public String getPublic() {
        return "PUBLIC";
    }

    @GetMapping("/secured")
    public String getSecured() {
        return "SECURED";
    }
}
