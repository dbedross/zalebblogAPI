package com.zaleb.zalebblog.controllers;

import com.zaleb.zalebblog.dtos.ArticleRequestDto;
import com.zaleb.zalebblog.dtos.ArticleResponseDto;
import com.zaleb.zalebblog.dtos.CommentRequestDto;
import com.zaleb.zalebblog.dtos.CommentResponseDto;
import com.zaleb.zalebblog.services.ArticleService;
import com.zaleb.zalebblog.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ArticleController {

    private final ArticleService articleService;
    private final JwtService jwtService;

    @GetMapping
    public Set<ArticleResponseDto> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping("/{articleId}/comment")
    public CommentResponseDto comment(@PathVariable("articleId") Long articleId,
                                      @RequestBody CommentRequestDto commentRequestDto) {
        return articleService.comment(articleId, commentRequestDto);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMIN')")
    public ArticleResponseDto createArticle(@RequestHeader("Authorization") String authHeader,
                                            @RequestBody ArticleRequestDto articleRequestDto) {
        String token = authHeader.substring(7);
        Long userId = jwtService.extractId(token);
        return articleService.createArticle(userId, articleRequestDto);
    }

    @GetMapping("/{articleId}")
    public ArticleResponseDto getArticle(@PathVariable("articleId") Long articleId) {
        return articleService.getArticle(articleId);
    }

    @GetMapping("/category/{categoryId}")
    public Set<ArticleResponseDto> getArticlesByCategory(@PathVariable("categoryId") Long categoryId){
        return articleService.getArticlesByCategory(categoryId);
    }

    @GetMapping("/user/{userId}")
    public Set<ArticleResponseDto> getArticlesByUser(@PathVariable("userId") Long userId){
        return articleService.getArticlesByUser(userId);
    }

    @GetMapping("/search")
    public Set<ArticleResponseDto> searchArticles(@RequestParam("q") String query) {
        return articleService.searchArticles(query);
    }

}
