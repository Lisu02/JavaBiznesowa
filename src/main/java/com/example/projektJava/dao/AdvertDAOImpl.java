package com.example.projektJava.dao;

import com.example.projektJava.model.Advert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdvertDAOImpl implements AdvertDAO{


    private EntityManager entityManager;


    @Autowired
    public AdvertDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(Advert theAdvert) {
        entityManager.persist(theAdvert);
    }

    @Override
    public Advert findById(Integer id) {
        return entityManager.find(Advert.class,id);
    }

    @Override
    public List<Advert> findAll() {
        TypedQuery<Advert> theQuery = entityManager.createQuery("FROM Advert ", Advert.class);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Advert theAdvert) {
        entityManager.merge(theAdvert);
    }

    @Override
    @Transactional
    public void delete(Advert theAdvert) {
        entityManager.remove(theAdvert);
    }
}
