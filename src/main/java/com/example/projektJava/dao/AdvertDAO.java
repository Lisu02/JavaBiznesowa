package com.example.projektJava.dao;

import com.example.projektJava.model.Advert;

import java.util.List;

public interface AdvertDAO {

    void save(Advert theAdvert);

    Advert findById(Integer id);

    List<Advert> findAll();

    void update(Advert theAdvert);

    void delete(Advert theAdvert);

}
