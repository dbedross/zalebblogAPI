package com.zaleb.zalebblog.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Table(name = "user_table")
@Entity
@NoArgsConstructor
@Data
@ToString(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Credentials credentials;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private List<Article> articles = new ArrayList<>();

}
