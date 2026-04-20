package com.zaleb.zalebblog.repositories;

import com.zaleb.zalebblog.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Set<Article> findAllByCategoriesId(Long categoryId);

    Set<Article> findAllByAuthorId(Long userId);

    Set<Article> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
}
