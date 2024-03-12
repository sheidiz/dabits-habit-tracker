package com.devshei.dabits.controllers;

import com.devshei.dabits.dto.Category;
import com.devshei.dabits.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PutMapping(path = "/categories/{id}")
    public ResponseEntity<Category> createUpdateCategory(
            @PathVariable final Long id,
            @RequestBody final Category category) {
        category.setId(id);

        final boolean isCategoryExists = categoryService.isCategoryExists(category);
        final Category savedCategory = categoryService.save(category);

        if (isCategoryExists) {
            return new ResponseEntity<>(savedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable final Long id) {
        final Optional<Category> foundCategory = categoryService.findById(id);
        return foundCategory.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<List<Category>> listCategories() {
        return new ResponseEntity<>(categoryService.listCategories(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/categories/{id}")
    public ResponseEntity deleteCategory(@PathVariable final Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
