package com.example.projektJava.dao;

import com.example.projektJava.model.Advert;
import com.example.projektJava.model.Users;

import java.util.List;

public interface UsersDAO {

    void save(Users user);

    void update(Users user);


    Users findByUsername(String username);

    List<Users> findAll();
}
