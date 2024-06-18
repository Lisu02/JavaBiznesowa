package com.example.projektJava.controller;

import com.example.projektJava.dao.AdvertDAO;
import com.example.projektJava.model.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping({ "/advert" })
public class AdvertController {


    @Autowired
    private AdvertDAO advertDAO;

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
        advertDAO.save(advert);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteAdvert(@PathVariable Long id){
        advertDAO.delete(advertDAO.findById(id));
        return "redirect:/";
    }


}
