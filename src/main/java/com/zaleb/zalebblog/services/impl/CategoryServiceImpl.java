package com.zaleb.zalebblog.services.impl;

import com.zaleb.zalebblog.dtos.CategoryDto;
import com.zaleb.zalebblog.dtos.CategoryRequestDto;
import com.zaleb.zalebblog.entities.Category;
import com.zaleb.zalebblog.exceptions.BadRequestException;
import com.zaleb.zalebblog.mappers.CategoryMapper;
import com.zaleb.zalebblog.repositories.CategoryRepository;
import com.zaleb.zalebblog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    @Transactional
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.entitiesToDtos(categories);
    }

    @Override
    public CategoryDto createCategory(CategoryRequestDto categoryRequestDto) {
        if (categoryRequestDto == null || categoryRequestDto.getName() == null){
            throw new BadRequestException("must have a name for the category");
        }
        Optional<Category> trying = categoryRepository.findByName(categoryRequestDto.getName());
        if (trying.isPresent()){
            throw new BadRequestException("already a category");
        }

        Category newCategory = categoryMapper.dtoToEntity(categoryRequestDto);
        return categoryMapper.entityToDto(categoryRepository.saveAndFlush(newCategory));
    }
}
