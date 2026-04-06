package com.zaleb.zalebblog.mappers;

import com.zaleb.zalebblog.dtos.CredentialsDto;
import com.zaleb.zalebblog.entities.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    Credentials dtoToEntity(CredentialsDto credentialsDto);
}
