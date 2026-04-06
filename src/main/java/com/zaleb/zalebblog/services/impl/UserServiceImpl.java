package com.zaleb.zalebblog.services.impl;

import com.zaleb.zalebblog.dtos.ArticleRequestDto;
import com.zaleb.zalebblog.dtos.ArticleResponseDto;
import com.zaleb.zalebblog.dtos.CredentialsDto;
import com.zaleb.zalebblog.dtos.LoginResponseDto;
import com.zaleb.zalebblog.dtos.UserResponseDto;
import com.zaleb.zalebblog.entities.Article;
import com.zaleb.zalebblog.entities.Category;
import com.zaleb.zalebblog.entities.Credentials;
import com.zaleb.zalebblog.entities.User;
import com.zaleb.zalebblog.exceptions.BadRequestException;
import com.zaleb.zalebblog.mappers.ArticleMapper;
import com.zaleb.zalebblog.mappers.CredentialsMapper;
import com.zaleb.zalebblog.mappers.UserMapper;
import com.zaleb.zalebblog.repositories.ArticleRepository;
import com.zaleb.zalebblog.repositories.CategoryRepository;
import com.zaleb.zalebblog.repositories.UserRepository;
import com.zaleb.zalebblog.services.JwtService;
import com.zaleb.zalebblog.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final CredentialsMapper credentialsMapper;
    private final UserMapper userMapper;
    private final ArticleMapper articleMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // ...existing code...

    private User findUser(String username) {
        Optional<User> user = userRepository.findByCredentialsUsername(username);
        if (user.isEmpty()) {
            throw new BadRequestException("username provided does not belong to a user");
        }
        return user.get();
    }

    @Override
    public LoginResponseDto login(CredentialsDto credentials) {
        if (credentials == null || credentials.getUsername() == null || credentials.getPassword() == null) {
            throw new BadRequestException("A username and password are required.");
        }

        Credentials credentialsToValidate = credentialsMapper.dtoToEntity(credentials);
        User userToValidate = findUser(credentialsToValidate.getUsername());
        if (!passwordEncoder.matches(credentials.getPassword(), userToValidate.getCredentials().getPassword())) {
            throw new BadRequestException("incorrect password");
        }

        String token = jwtService.generateToken(userToValidate);
        return new LoginResponseDto(userToValidate.getId(), userToValidate.getName(), token);
    }

    @Override
    @Transactional
    public ArticleResponseDto createArticle(Long userId, ArticleRequestDto articleRequestDto) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new BadRequestException("you're not real");
        }

        Article newArticle = articleMapper.dtoToEntity(articleRequestDto);
        newArticle.setAuthor(user.get());

        List<Category> articleCategories = new ArrayList<>(categoryRepository.findAllById(articleRequestDto.getCategoryIds()));

        newArticle.setCategories(articleCategories);
        return articleMapper.entityToDto(articleRepository.save(newArticle));
    }

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
