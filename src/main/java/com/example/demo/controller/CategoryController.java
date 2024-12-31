package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        logAction("GET /categories");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        logAction("GET /categories/" + id);
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        logAction("POST /categories");
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        logAction("DELETE /categories/" + id);
        categoryService.deleteCategory(id);
    }

    private void logAction(String action) {
        try (FileWriter writer = new FileWriter("logs.txt", true)) {
            writer.write(action + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

