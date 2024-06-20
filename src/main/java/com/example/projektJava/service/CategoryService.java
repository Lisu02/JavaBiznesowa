package com.example.projektJava.service;

import com.example.projektJava.dao.CategoryDAO;
import com.example.projektJava.model.Category;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryDAO categoryDAO;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Category findById(Long id) {
        return categoryDAO.findById(id);
    }
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
    public void add(Category category) {
        categoryDAO.save(category);
        logger.debug("New category was saved succesfully! ->" + category.getName());
    }
    public void update(Category category) {
        categoryDAO.update(category);
        logger.debug("Category was updated succesfully! ->" + category.getName());
    }
    public void delete(Category category) {
        categoryDAO.delete(category);
        logger.debug("Category : " + category.getName() + " was deleted!");
    }
}
