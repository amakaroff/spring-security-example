package dev.makarov.spring.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin!";
    }

    @ResponseBody
    @Secured("ROLE_ADMIN")
    @GetMapping("/admin2")
    public String admin2() {
        return "Hello Admin!";
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin3")
    public String admin3() {
        return "Hello Admin!";
    }
}
