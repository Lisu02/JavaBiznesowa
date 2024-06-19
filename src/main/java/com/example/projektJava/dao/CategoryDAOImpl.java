package com.example.projektJava.dao;

import com.example.projektJava.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private EntityManager entityManager;
    @Autowired
    public CategoryDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Category category) {
        entityManager.persist(category);
    }

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class,id);
    }

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> theQuery = entityManager.createQuery("FROM Category ", Category.class);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Override
    @Transactional
    public void delete(Category category) {
        entityManager.remove(category);
    }
}
