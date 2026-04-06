package com.zaleb.zalebblog.services;

import com.zaleb.zalebblog.dtos.CategoryDto;
import com.zaleb.zalebblog.dtos.CategoryRequestDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto createCategory(CategoryRequestDto categoryRequestDto);
}
