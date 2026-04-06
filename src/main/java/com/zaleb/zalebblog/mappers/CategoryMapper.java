package com.zaleb.zalebblog.mappers;

import com.zaleb.zalebblog.dtos.CategoryDto;
import com.zaleb.zalebblog.dtos.CategoryRequestDto;
import com.zaleb.zalebblog.entities.Category;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category dtoToEntity(CategoryDto categoryDto);

    Category dtoToEntity(CategoryRequestDto categoryRequestDto);

    Set<Category> dtosToEntities(Set<CategoryDto> categoryDtos);

    List<CategoryDto> entitiesToDtos(List<Category> allCategories);

    CategoryDto entityToDto(Category category);
}
