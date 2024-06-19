package com.example.projektJava.dao;

import com.example.projektJava.model.Category;

import java.util.List;

public interface CategoryDAO {

    void save(Category category);

    Category findById(Long id);

    List<Category> findAll();

    void update(Category category);

    void delete(Category category);
}
