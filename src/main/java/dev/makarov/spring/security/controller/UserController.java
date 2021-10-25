package dev.makarov.spring.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @ResponseBody
    @GetMapping("/user")
    public String userEndpoint() {
        return "Hello User \"" + getCurrentUserName() + "\"!";
    }

    @ResponseBody
    @GetMapping("/user2")
    @Secured("ROLE_USER")
    public String userEndpoint2() {
        return "Hello User \"" + getCurrentUserName() + "\"!";
    }

    @ResponseBody
    @GetMapping("/user3")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userEndpoint3() {
        return "Hello User \"" + getCurrentUserName() + "\"!";
    }

    private String getCurrentUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();

        return userDetails.getUsername();
    }
}
