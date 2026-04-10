package com.zaleb.zalebblog.controllers;

import com.zaleb.zalebblog.dtos.UserResponseDto;
import com.zaleb.zalebblog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Set<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }
}
