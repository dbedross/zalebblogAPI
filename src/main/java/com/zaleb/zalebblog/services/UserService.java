package com.zaleb.zalebblog.services;

import com.zaleb.zalebblog.dtos.UserResponseDto;

import java.util.Set;

public interface UserService {

    Set<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long userId);
}
