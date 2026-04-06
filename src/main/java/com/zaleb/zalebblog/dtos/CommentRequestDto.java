package com.zaleb.zalebblog.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentRequestDto {

    private String name;

    private String content;
}
