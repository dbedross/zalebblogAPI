package com.zaleb.zalebblog.mappers;

import com.zaleb.zalebblog.dtos.CommentRequestDto;
import com.zaleb.zalebblog.dtos.CommentResponseDto;
import com.zaleb.zalebblog.entities.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment dtoToEntity(CommentRequestDto commentRequestDto);

    CommentResponseDto entityToDto(Comment comment);
}
