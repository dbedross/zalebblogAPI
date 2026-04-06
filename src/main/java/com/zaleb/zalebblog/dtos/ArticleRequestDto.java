package com.zaleb.zalebblog.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class ArticleRequestDto {

    private String title;

    private String content;

    private List<Long> categoryIds;

}
