package com.zaleb.zalebblog.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String role;

    private List<SimplifiedArticleDto> articles;
}
