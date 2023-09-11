package com.ra.web.service;

import com.ra.web.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findByName(String key);
    Category findId(String id);
    void add(Category category);
    void edit(Category category);
}
