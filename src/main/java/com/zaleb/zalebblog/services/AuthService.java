package com.zaleb.zalebblog.services;

import com.zaleb.zalebblog.dtos.CredentialsDto;
import com.zaleb.zalebblog.dtos.LoginResponseDto;
import com.zaleb.zalebblog.dtos.UserRequestDto;

public interface AuthService {

    LoginResponseDto login(CredentialsDto credentials);

    LoginResponseDto register(UserRequestDto userRequestDto);
}

