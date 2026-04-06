package com.zaleb.zalebblog.services;

import com.zaleb.zalebblog.dtos.ArticleRequestDto;
import com.zaleb.zalebblog.dtos.ArticleResponseDto;
import com.zaleb.zalebblog.dtos.CredentialsDto;
import com.zaleb.zalebblog.dtos.UserResponseDto;

import java.util.Set;

public interface UserService {

    UserResponseDto login(CredentialsDto credentials);

    ArticleResponseDto createArticle(Long userId, ArticleRequestDto articleRequestDto);

    Set<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long userId);
}
