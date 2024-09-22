package hacaton.controller;

import hacaton.model.User;
import hacaton.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import hacaton.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDto user) {
        return userService.registerNewUser(user);
    }

    @GetMapping("/me")
    public User getCurrentUser(@RequestParam String username) {
        return userService.findByUsername(username);
    }
}

