package com.zaleb.zalebblog.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class ArticleResponseDto {

    private Long id;

    private String title;

    private String content;

    private UserResponseDto author;

    private List<CommentResponseDto> comments;

    private List<SimplifiedCategoryDto> categories;
}
