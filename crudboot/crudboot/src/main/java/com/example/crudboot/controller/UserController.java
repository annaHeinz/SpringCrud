package com.example.crudboot.controller;

import com.example.crudboot.model.User;
import com.example.crudboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user";
    }
    @GetMapping("/user/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("allowDelete", true);
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }
    @PostMapping("/user/{id}")
    public String updateUser(User user, @PathVariable Long id) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/user";
    }
    @PostMapping("/user/add")
    public String addUser(User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeById(id);
        return "redirect:/user";
    }


}
