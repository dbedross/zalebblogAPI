package com.zaleb.zalebblog.controllers;

import com.zaleb.zalebblog.dtos.ArticleRequestDto;
import com.zaleb.zalebblog.dtos.ArticleResponseDto;
import com.zaleb.zalebblog.dtos.CredentialsDto;
import com.zaleb.zalebblog.dtos.LoginResponseDto;
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

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody CredentialsDto credentials) {
        return userService.login(credentials);
    }

    @PostMapping("/{userId}/article")
    public ArticleResponseDto createArticle(@PathVariable("userId") Long userId, @RequestBody ArticleRequestDto articleRequestDto) {
        return userService.createArticle(userId, articleRequestDto);
    }

    @GetMapping
    public Set<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }
}
