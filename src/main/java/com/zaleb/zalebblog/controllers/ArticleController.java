package com.zaleb.zalebblog.controllers;

import com.zaleb.zalebblog.dtos.ArticleResponseDto;
import com.zaleb.zalebblog.dtos.CommentRequestDto;
import com.zaleb.zalebblog.dtos.CommentResponseDto;
import com.zaleb.zalebblog.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public Set<ArticleResponseDto> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping("/{articleId}/comment")
    public CommentResponseDto comment(@PathVariable("articleId") Long articleId, @RequestBody CommentRequestDto commentRequestDto) {
        return articleService.comment(articleId, commentRequestDto);
    }

}
