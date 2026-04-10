package com.zaleb.zalebblog.services;

import com.zaleb.zalebblog.dtos.ArticleRequestDto;
import com.zaleb.zalebblog.dtos.ArticleResponseDto;
import com.zaleb.zalebblog.dtos.CommentRequestDto;
import com.zaleb.zalebblog.dtos.CommentResponseDto;

import java.util.Set;

public interface ArticleService {

    Set<ArticleResponseDto> getAllArticles();

    CommentResponseDto comment(Long articleId, CommentRequestDto commentRequestDto);

    ArticleResponseDto createArticle(Long userId, ArticleRequestDto articleRequestDto);
}
