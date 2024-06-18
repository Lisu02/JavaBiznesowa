package com.example.projektJava.controller;

import com.example.projektJava.dao.AdvertDAO;
import com.example.projektJava.model.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainPageController {


    @Autowired
    private AdvertDAO advertDAO;

    @GetMapping("/")
    public String getMainPage(Model model){
        List<Advert> advertList = advertDAO.findAll();
        model.addAttribute("adverts",advertList);
        //User??
        for(Advert tmpAdvert: advertList){
            System.out.println(tmpAdvert.getInformation());
        }
        return "main-page";
    }

    @GetMapping("/showAdvertForm")
    public String addAdvert(Model model){

        model.addAttribute("advert",new Advert());

        return "add-advert";
    }

    @PostMapping("/processAdvertForm")
    public String processAdvertForm(@ModelAttribute("advert") Advert advert){
        System.out.println(advert.getId() + " | " + advert.getInformation());
        advertDAO.save(advert);
        return "redirect:/";
    }


}
