package com.example.projektJava.service;

import com.example.projektJava.dao.AdvertDAO;
import com.example.projektJava.dao.UsersDAO;
import com.example.projektJava.model.Advert;
import com.example.projektJava.model.Users;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdvertService {


    private AdvertDAO advertDAO;
    private UsersDAO usersDAO;
    private EmailService emailService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AdvertService(AdvertDAO advertDAO,UsersDAO usersDAO,EmailService emailService){
        this.advertDAO = advertDAO;
        this.usersDAO = usersDAO;
        this.emailService = emailService;
    }


    //@Async
    public void processAdvertForm(Advert advert, Authentication authentication){
        String userName = authentication.getName();
        Users user = usersDAO.findByUsername(userName);

        //Hibernate.initialize(user.getAdverts());


        advert.setAccepted(false);
        advert.setCreationDate(LocalDate.now());
        advert.setUser(user);

        List<Advert> userAdverts = user.getAdverts();
        userAdverts.add(advert);
        user.setAdverts(userAdverts);

        advertDAO.save(advert);
        logger.debug("New advert was successfully created by user: " + userName);
        emailService.sendEmail("uzytkownik@gmail.com", advert.getTitle(), advert.getInformation() + "\n" + "Twoje ogloszenie oczekuje na zatwierdzenie");
    }

    public void updateAdvertForm(Advert advert){
        Advert advertTMP = advertDAO.findById(advert.getId());
        advert.setCreationDate(advertTMP.getCreationDate());
        advert.setAccepted(false);
        advert.setUser(advertTMP.getUser());
        logger.debug("Advert UPDATE \n" + "Old advert -> " + advertTMP + "\n" + "New advert -> " + advert);
        advertDAO.update(advert);
    }

    public void acceptAdvert(Long id){
        Advert advert = advertDAO.findById(id);
        advert.setAccepted(true);
        advertDAO.update(advert);
        logger.debug("Advert id{" + id + "} was accepted by admin");
    }

    public void deleteAdvert(Long id){
        advertDAO.delete(advertDAO.findById(id));
        logger.debug("Advert id{" + id + "} was deleted");
    }
}
