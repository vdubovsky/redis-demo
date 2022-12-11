package io.vdubovsky.redisdemo.controller;

import io.vdubovsky.redisdemo.model.User;
import io.vdubovsky.redisdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User userDto){
        return userService.create(userDto);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id){
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }

}
