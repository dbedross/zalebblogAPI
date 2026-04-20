package com.zaleb.zalebblog.services.impl;

import com.zaleb.zalebblog.dtos.CredentialsDto;
import com.zaleb.zalebblog.dtos.LoginResponseDto;
import com.zaleb.zalebblog.dtos.UserRequestDto;
import com.zaleb.zalebblog.entities.Credentials;
import com.zaleb.zalebblog.entities.User;
import com.zaleb.zalebblog.exceptions.BadRequestException;
import com.zaleb.zalebblog.mappers.CredentialsMapper;
import com.zaleb.zalebblog.mappers.LoginMapper;
import com.zaleb.zalebblog.repositories.UserRepository;
import com.zaleb.zalebblog.services.AuthService;
import com.zaleb.zalebblog.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CredentialsMapper credentialsMapper;
    private final LoginMapper loginMapper;

    private User findUser(String username) {
        Optional<User> user = userRepository.findByCredentialsUsername(username);
        if (user.isEmpty()) {
            throw new BadRequestException("Username not found.");
        }
        return user.get();
    }

    @Override
    public LoginResponseDto login(CredentialsDto credentials) {
        if (credentials == null || credentials.getUsername() == null || credentials.getPassword() == null) {
            throw new BadRequestException("A username and password are required.");
        }

        User userToValidate = findUser(credentials.getUsername());
        if (!passwordEncoder.matches(credentials.getPassword(), userToValidate.getCredentials().getPassword())) {
            throw new BadRequestException("Incorrect password.");
        }

        String token = jwtService.generateToken(userToValidate);
        LoginResponseDto response = loginMapper.entityToDTO(userToValidate);
        response.setToken(token);
        return response;
    }

    @Override
    @Transactional
    public LoginResponseDto register(UserRequestDto userRequestDto) {
        if (userRequestDto == null
                || userRequestDto.getCredentials() == null
                || userRequestDto.getCredentials().getUsername() == null
                || userRequestDto.getCredentials().getUsername().isBlank()
                || userRequestDto.getCredentials().getPassword() == null
                || userRequestDto.getCredentials().getPassword().isBlank()
                || userRequestDto.getName() == null
                || userRequestDto.getName().isBlank()) {
            throw new BadRequestException("Name, username, and password are all required.");
        }

        String username = userRequestDto.getCredentials().getUsername();
        if (userRepository.findByCredentialsUsername(username).isPresent()) {
            throw new BadRequestException("Username is already taken.");
        }

        Credentials credentials = credentialsMapper.dtoToEntity(userRequestDto.getCredentials());
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));

        User newUser = new User();
        newUser.setName(userRequestDto.getName());
        newUser.setCredentials(credentials);

        User savedUser = userRepository.save(newUser);

        String token = jwtService.generateToken(savedUser);
        LoginResponseDto response = loginMapper.entityToDTO(savedUser);
        response.setToken(token);
        return response;
    }
}

