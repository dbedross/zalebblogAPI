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

    ArticleResponseDto getArticle(Long articleId);

    Set<ArticleResponseDto> getArticlesByCategory(Long categoryId);

    Set<ArticleResponseDto> getArticlesByUser(Long userId);

    Set<ArticleResponseDto> searchArticles(String query);
}
