package com.example.projektJava.controller;

import com.example.projektJava.dao.AdvertDAO;
import com.example.projektJava.dao.UsersDAO;
import com.example.projektJava.model.Advert;
import com.example.projektJava.service.AdvertService;
import com.example.projektJava.service.CategoryService;
import com.example.projektJava.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@RequestMapping({ "/advert" })
public class AdvertController {



    private AdvertDAO advertDAO;
    private UsersDAO usersDAO;
    private EmailService emailService;
    private AdvertService advertService;
    private CategoryService categoryService;

    @Autowired
    public AdvertController(AdvertDAO advertDao, EmailService emailService, UsersDAO usersDAO,AdvertService advertService, CategoryService categoryService){
        this.advertDAO = advertDao;
        this.emailService = emailService;
        this.usersDAO = usersDAO;
        this.advertService = advertService;
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public String getMainPage(Model model){
        List<Advert> advertList = advertDAO.findByAccepted(true);
        model.addAttribute("adverts",advertList);
        //emailService.testowySerwisLogowanie();
        //User??

        return "main-page";
    }

    @GetMapping("/user-adverts")
    public String getUserAdverts(Model model){
        List<Advert> advertList = advertDAO.findByUser();
        model.addAttribute("adverts",advertList);

        return "advert/user-advert-page";
    }

    @GetMapping("/waiting-adverts")
    public String getWaitingAdverts(Model model){
        List<Advert> advertList = advertDAO.findByAccepted(false);
        model.addAttribute("adverts",advertList);

        return "advert/moderate-advert";
    }

    @GetMapping("/add")
    public String addAdvert(Model model){

        model.addAttribute("advert",new Advert());
        model.addAttribute("categories",categoryService.findAll());

        return "advert/add-advert";
    }

    @PostMapping("/add")
    public String processAdvertForm(@ModelAttribute("advert") Advert advert, @ModelAttribute("category_id") Long category_id){
        //Pobiernie użytkownika i listy jego ogłoszeń
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        advert.setCategory(categoryService.findById(category_id));

        advertService.processAdvertForm(advert,authentication);
        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String updateAdvert(Model model, @PathVariable Long id){

        model.addAttribute("advert",advertDAO.findById(id));
        model.addAttribute("categories",categoryService.findAll());

        return "advert/edit-advert";

    }

    @PostMapping("/update/{id}")
    public String updateAdvertForm(@ModelAttribute("advert") Advert advert, @ModelAttribute("category_id") Long category_id){
        Advert advertTMP = advertDAO.findById(advert.getId());
        advert.setCreationDate(advertTMP.getCreationDate());
        advert.setUser(advertTMP.getUser());
        advert.setCategory(categoryService.findById(category_id));
        advert.setAccepted(false);

        advertService.updateAdvertForm(advert);
        return "redirect:/";
    }

    @PostMapping("/accept/{id}")
    public String acceptAdvert(@PathVariable Long id){

        advertService.acceptAdvert(id);
        return "redirect:/waiting-adverts";
    }

    @PostMapping("/delete/{id}")

    public String deleteAdvert(@PathVariable Long id){

        advertService.deleteAdvert(id);
        return "redirect:/";
    }


}
