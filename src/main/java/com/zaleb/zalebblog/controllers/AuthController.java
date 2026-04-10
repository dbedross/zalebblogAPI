package com.zaleb.zalebblog.controllers;

import com.zaleb.zalebblog.dtos.CredentialsDto;
import com.zaleb.zalebblog.dtos.LoginResponseDto;
import com.zaleb.zalebblog.dtos.UserRequestDto;
import com.zaleb.zalebblog.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody CredentialsDto credentials) {
        return authService.login(credentials);
    }

    @PostMapping("/register")
    public LoginResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        return authService.register(userRequestDto);
    }
}

