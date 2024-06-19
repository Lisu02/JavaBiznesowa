package com.example.projektJava.dao;

import com.example.projektJava.model.Advert;
import com.example.projektJava.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {


    private EntityManager entityManager;


    @Autowired
    public UsersDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Users user) {
        entityManager.persist(user);
    }

    @Override
    public void update(Users user) {
        entityManager.merge(user);
    }

    @Override
    public Users findByUsername(String username) {
        return entityManager.find(Users.class,username);
    }

    @Override
    public List<Users> findAll() {
        TypedQuery<Users> theQuery = entityManager.createQuery("FROM Users ", Users.class);

        return theQuery.getResultList();
    }
}
