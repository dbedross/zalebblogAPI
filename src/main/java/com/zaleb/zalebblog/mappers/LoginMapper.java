package com.zaleb.zalebblog.mappers;

import com.zaleb.zalebblog.dtos.LoginResponseDto;
import com.zaleb.zalebblog.entities.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginResponseDto entityToDTO(User user);
}
