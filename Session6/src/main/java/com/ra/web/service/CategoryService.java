package com.ra.web.service;

import com.ra.web.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findByName(String key);
    void add(Category category);
}
