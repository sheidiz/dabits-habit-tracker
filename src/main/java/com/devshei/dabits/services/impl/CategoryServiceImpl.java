package com.devshei.dabits.services.impl;

import com.devshei.dabits.domain.CategoryEntity;
import com.devshei.dabits.dto.Category;
import com.devshei.dabits.repositories.CategoryRepository;
import com.devshei.dabits.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isCategoryExists(Category category) {
        return categoryRepository.existsById(category.getId());
    }

    @Override
    public Category save(Category category) {
        final CategoryEntity categoryEntity = categoryToCategoryEntity(category);
        final CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);

        return categoryEntityToCategory(savedCategoryEntity);
    }

    private Category categoryEntityToCategory(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(categoryEntity.getId())
                .title(categoryEntity.getTitle())
                .color(categoryEntity.getColor())
                .build();
    }

    private CategoryEntity categoryToCategoryEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .title(category.getTitle())
                .color(category.getColor())
                .build();
    }

    @Override
    public Optional<Category> findById(Long id) {
        final Optional<CategoryEntity> foundCategory = categoryRepository.findById(id);
        return foundCategory.map(this::categoryEntityToCategory);
    }

    @Override
    public List<Category> listCategories() {
        final List<CategoryEntity> foundCategories = categoryRepository.findAll();
        return foundCategories.stream()
                .map(this::categoryEntityToCategory)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategoryById(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ex) {
            log.debug("Attempted to delete non-existing category", ex);
        }
    }
}
