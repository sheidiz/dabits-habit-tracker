package com.devshei.dabits.services;

import com.devshei.dabits.dto.Category;
import com.devshei.dabits.dto.User;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    boolean isCategoryExists(Category category);

    Category save(Category category);

    Optional<Category> findById(Long id);

    List<Category> listCategories();

    void deleteCategoryById(Long id);
}
