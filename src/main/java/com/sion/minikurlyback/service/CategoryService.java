package com.sion.minikurlyback.service;

import com.sion.minikurlyback.entity.Category;
import com.sion.minikurlyback.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Category> findAllByParentId(Long id) {
        Category parent = findById(id);

        List<Category> categories = new ArrayList<>();
        if (parent.getLevel() == 3) {
            categories.add(parent);
            return categories;
        }

        Sort sort = sortByCategoryOrder();
        return categoryRepository.findAllByParentId(id, sort);
    }

    private Sort sortByCategoryOrder() {
        return Sort.by(Sort.Direction.ASC, "categoryOrder");
    }

}
