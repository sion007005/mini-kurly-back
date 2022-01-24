package com.sion.minikurlyback.service;

import com.sion.minikurlyback.entity.Category;
import com.sion.minikurlyback.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(IllegalArgumentException::new);
    }
}
