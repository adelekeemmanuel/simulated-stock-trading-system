package com.pl.stocktradingplatform.controller;

import com.pl.stocktradingplatform.entity.User;
import com.pl.stocktradingplatform.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService u) {
        this.userService = u;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public User create(@RequestBody User u) {
        return userService.createUser(u);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User u) {
        return userService.updateUser(id, u);
    }
}
