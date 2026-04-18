package com.zaleb.zalebblog.controllers;

import com.zaleb.zalebblog.dtos.CategoryDto;
import com.zaleb.zalebblog.dtos.CategoryRequestDto;
import com.zaleb.zalebblog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('AUTHOR')")
    public CategoryDto createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return categoryService.createCategory(categoryRequestDto);
    }
}
