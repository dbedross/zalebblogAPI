package com.zaleb.zalebblog.services.impl;

import com.zaleb.zalebblog.dtos.UserResponseDto;
import com.zaleb.zalebblog.entities.User;
import com.zaleb.zalebblog.exceptions.BadRequestException;
import com.zaleb.zalebblog.mappers.UserMapper;
import com.zaleb.zalebblog.repositories.UserRepository;
import com.zaleb.zalebblog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // ...existing code...

    @Override
    @Transactional
    public Set<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        Set<User> allUsers = new HashSet<>(userList);
        return userMapper.entitiesToDtos(allUsers);
    }

    @Override
    @Transactional
    public UserResponseDto getUserById(Long userId){
        Optional<User> userToFind = userRepository.findById(userId);
        if (userToFind.isEmpty()){
            throw new BadRequestException("not a real user");
        }
        User user = userToFind.get();
        return userMapper.entityToDto(user);
    }
}
