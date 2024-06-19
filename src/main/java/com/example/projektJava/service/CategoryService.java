package com.example.projektJava.service;

import com.example.projektJava.dao.CategoryDAO;
import com.example.projektJava.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryDAO categoryDAO;

    public Category findById(Long id) {
        return categoryDAO.findById(id);
    }
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
    public void add(Category category) {
        categoryDAO.save(category);
    }
    public void update(Category category) {
        categoryDAO.update(category);
    }
    public void delete(Category category) {
        categoryDAO.delete(category);
    }
}
