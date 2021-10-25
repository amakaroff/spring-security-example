package dev.makarov.spring.security.controller;

import dev.makarov.spring.security.UserService;
import dev.makarov.spring.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping(value = "/register/user")
    public String register(@RequestBody User user) {
        userService.registerUser(user, false);
        return "redirect:/";
    }

    @PostMapping(value = "/register/admin")
    public String registerAdmin(@RequestBody User user) {
        userService.registerUser(user, true);
        return "redirect:/";
    }
}
