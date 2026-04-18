package com.zaleb.zalebblog.services.impl;

import com.zaleb.zalebblog.dtos.RoleUpdateDto;
import com.zaleb.zalebblog.dtos.UserResponseDto;
import com.zaleb.zalebblog.entities.User;
import com.zaleb.zalebblog.exceptions.BadRequestException;
import com.zaleb.zalebblog.mappers.UserMapper;
import com.zaleb.zalebblog.repositories.UserRepository;
import com.zaleb.zalebblog.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User findUser(String username) {
        Optional<User> user = userRepository.findByCredentialsUsername(username);
        if (user.isEmpty()) {
            throw new BadRequestException("Username not found.");
        }
        return user.get();
    }

    private User findUserId(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new BadRequestException("User ID not found");
        }
        return user.get();
    }

    @Override
    public UserResponseDto updateUserRole(Long userId, RoleUpdateDto role) {
        if(userId == null){
            throw new BadRequestException("User ID is required.");
        }

        User userToUpdate = findUserId(userId);
        if(userToUpdate.getRole().equals(role.getRole())){
            throw new BadRequestException("User already has the role of " + role);
        }
        userToUpdate.setRole(role.getRole());
        userRepository.save(userToUpdate);
        return userMapper.entityToDto(userToUpdate);
    }
}
