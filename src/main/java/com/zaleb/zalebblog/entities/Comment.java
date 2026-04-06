package com.zaleb.zalebblog.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
