package com.example.projektJava.controller;

import com.example.projektJava.dao.AdvertDAO;
import com.example.projektJava.dao.UsersDAO;
import com.example.projektJava.model.Advert;
import com.example.projektJava.model.Users;
import com.example.projektJava.service.EmailService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Controller
//@RequestMapping({ "/advert" })
public class AdvertController {



    private AdvertDAO advertDAO;
    private UsersDAO usersDAO;
    private EmailService emailService;

    @Autowired
    public AdvertController(AdvertDAO advertDao, EmailService emailService, UsersDAO usersDAO){
        this.advertDAO = advertDao;
        this.emailService = emailService;
        this.usersDAO = usersDAO;
    }


    @GetMapping("/")
    public String getMainPage(Model model){
        List<Advert> advertList = advertDAO.findAll();
        model.addAttribute("adverts",advertList);
        //User??

        return "main-page";
    }

    @GetMapping("/add")
    public String addAdvert(Model model){

        model.addAttribute("advert",new Advert());

        return "add-advert";
    }

    @PostMapping("/add")
    public String processAdvertForm(@ModelAttribute("advert") Advert advert){
        //Pobiernie użytkownika i listy jego ogłoszeń
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Users user = usersDAO.findByUsername(currentName);
        List<Advert> advertList = user.getAdverts();
        //    if (advertList == null){advertList = new LinkedList<Advert>();}
        advert.setAccepted(false);
        advert.setCreationDate(LocalDate.now());
        advert.setUser(user);
        advertList.add(advert);
        user.setAdverts(advertList);
        advertDAO.save(advert);
        //usersDAO.update(user);



        //WYSYŁANIE EMAILI
        //emailService.sendEmail("uzytkownik@gmail.com",advert.getTitle(),advert.getInformation());
        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String updateAdvert(Model model, @PathVariable Long id){

        model.addAttribute("advert",advertDAO.findById(id));

        return "edit-advert";
    }

    @PostMapping("/update/{id}")
    public String updateAdvertForm(@ModelAttribute("advert") Advert advert){
        Advert advertTMP = advertDAO.findById(advert.getId());
        advert.setCreationDate(advertTMP.getCreationDate());
        advertDAO.update(advert);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteAdvert(@PathVariable Long id){
        advertDAO.delete(advertDAO.findById(id));
        return "redirect:/";
    }


}
