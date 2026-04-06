package com.zaleb.zalebblog.mappers;

import com.zaleb.zalebblog.dtos.ArticleRequestDto;
import com.zaleb.zalebblog.dtos.ArticleResponseDto;
import com.zaleb.zalebblog.entities.Article;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    Article dtoToEntity(ArticleRequestDto articleRequestDto);

    ArticleResponseDto entityToDto(Article article);

    Set<ArticleResponseDto> entitiesToDtos(Set<Article> allArticles);
}
