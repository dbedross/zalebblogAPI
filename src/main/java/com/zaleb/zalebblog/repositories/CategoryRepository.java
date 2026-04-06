package com.zaleb.zalebblog.repositories;

import com.zaleb.zalebblog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findAllByIdIn(List<Long> categoryIds);

    Optional<Category> findByName(String name);
}
