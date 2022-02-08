package com.sion.minikurlyback.controller;

import com.sion.minikurlyback.entity.Category;
import com.sion.minikurlyback.repository.CategoryRepository;
import com.sion.minikurlyback.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/category/create")
    public ResponseEntity<Category> create(Category category) {
        Category savedCategory = categoryService.create(category);
        return ResponseEntity.ok(savedCategory);
    }

    /**
     * @return 전체 카테고리 리스트 반환
     */
    @GetMapping("/category/list")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return ResponseEntity.ok(categoryList);
    }

    /**
     * 부모카테고리에 속한 자식카테고리 리스트 반환
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Category>> findAllByParentId(@PathVariable("id") Long id) {
        List<Category> categoryList = categoryService.findAllByParentId(id);
        return ResponseEntity.ok(categoryList);
    }

}
