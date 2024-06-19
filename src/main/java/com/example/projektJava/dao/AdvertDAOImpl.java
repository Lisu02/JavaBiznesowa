package com.example.projektJava.dao;

import com.example.projektJava.model.Advert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Advert findById(Long id) {
        return entityManager.find(Advert.class,id);
    }


    @Override
    public List<Advert> findAll() {
        TypedQuery<Advert> theQuery = entityManager.createQuery("FROM Advert", Advert.class);

        return theQuery.getResultList();
    }
    @Override
    public List<Advert> findByAccepted(Boolean accepted) {
        TypedQuery<Advert> theQuery = entityManager.createQuery("FROM Advert WHERE accepted = :accepted", Advert.class);
        theQuery.setParameter("accepted", accepted);

        return theQuery.getResultList();
    }
    @Override
    public List<Advert> findByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }

        if (username == null || username.isEmpty()) {
            throw new IllegalStateException("No authenticated user found");
        }

        TypedQuery<Advert> theQuery = entityManager.createQuery(
                "SELECT a FROM Advert a WHERE a.user.username = :username", Advert.class);
        theQuery.setParameter("username", username);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public Advert update(Advert theAdvert) {
        return entityManager.merge(theAdvert);
    }

    @Override
    @Transactional
    public void delete(Advert theAdvert) {
        entityManager.remove(theAdvert);
    }
}
