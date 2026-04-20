package com.zaleb.zalebblog.services.impl;

import com.zaleb.zalebblog.dtos.ArticleRequestDto;
import com.zaleb.zalebblog.dtos.ArticleResponseDto;
import com.zaleb.zalebblog.dtos.CommentRequestDto;
import com.zaleb.zalebblog.dtos.CommentResponseDto;
import com.zaleb.zalebblog.entities.Article;
import com.zaleb.zalebblog.entities.Category;
import com.zaleb.zalebblog.entities.Comment;
import com.zaleb.zalebblog.entities.User;
import com.zaleb.zalebblog.exceptions.BadRequestException;
import com.zaleb.zalebblog.mappers.ArticleMapper;
import com.zaleb.zalebblog.mappers.CommentMapper;
import com.zaleb.zalebblog.repositories.ArticleRepository;
import com.zaleb.zalebblog.repositories.CategoryRepository;
import com.zaleb.zalebblog.repositories.CommentRepository;
import com.zaleb.zalebblog.repositories.UserRepository;
import com.zaleb.zalebblog.services.ArticleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;


    @Override
    public Set<ArticleResponseDto> getAllArticles() {
        List<Article> articleList = articleRepository.findAll();
        Set<Article> allArticles = new HashSet<>(articleList);
        return articleMapper.entitiesToDtos(allArticles);
    }

    @Override
    public CommentResponseDto comment(Long articleId, CommentRequestDto commentRequestDto) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isEmpty()) {
            throw new BadRequestException("that article isn't real");
        }
        Article articleToComment = article.get();
        Comment comment = commentMapper.dtoToEntity(commentRequestDto);
        articleToComment.getComments().add(comment);
        articleRepository.saveAndFlush(articleToComment);
        comment.setArticle(articleToComment);

        return commentMapper.entityToDto(commentRepository.saveAndFlush(comment));
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
    public ArticleResponseDto getArticle(Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        if(article.isEmpty()) {
            throw new BadRequestException("that article isn't real");
        }
        return articleMapper.entityToDto(article.get());
    }

    @Override
    public Set<ArticleResponseDto> getArticlesByCategory(Long categoryId) {
        Set<Article> articles = articleRepository.findAllByCategoriesId(categoryId);
        if(articles.isEmpty()) {
            throw new BadRequestException("no articles in this category");
        }
        return articleMapper.entitiesToDtos(articles);
    }

    @Override
    public Set<ArticleResponseDto> getArticlesByUser(Long userId) {
        Set<Article> articles = articleRepository.findAllByAuthorId(userId);
        if(articles.isEmpty()){
            throw new BadRequestException("no articles by this user");
        }
        return articleMapper.entitiesToDtos(articles);
    }

    @Override
    public Set<ArticleResponseDto> searchArticles(String query) {
        if (query == null || query.isBlank()) {
            throw new BadRequestException("Search query cannot be empty.");
        }
        Set<Article> results = articleRepository.findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(query, query);
        if (results.isEmpty()) {
            throw new BadRequestException("No articles found matching that search.");
        }
        return articleMapper.entitiesToDtos(results);
    }
}
