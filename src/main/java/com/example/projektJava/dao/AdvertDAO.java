package com.example.projektJava.dao;

import com.example.projektJava.model.Advert;

import java.util.List;

public interface AdvertDAO {

    void save(Advert theAdvert);

    Advert findById(Long id);


    List<Advert> findAll();

    List<Advert> findByUser();

    List<Advert> findByAccepted(Boolean accepted);

    void update(Advert theAdvert);

    void delete(Advert theAdvert);

}
