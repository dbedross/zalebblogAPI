package com.zaleb.zalebblog.mappers;

import com.zaleb.zalebblog.dtos.UserResponseDto;
import com.zaleb.zalebblog.entities.User;
import org.mapstruct.Mapper;

import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto entityToDto(User user);

    Set<UserResponseDto> entitiesToDtos(Set<User> allUsers);
}
