package com.zaleb.zalebblog.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentResponseDto {

    private Long id;

    private String name;

    private String content;
}
