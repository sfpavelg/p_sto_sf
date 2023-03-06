package com.javamentor.qa.platform.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class UsersController {

    /**
     * Page of all users
     */
    @GetMapping("/users")
    public String showUsers() {
        return "AllUsers";
    }

}
