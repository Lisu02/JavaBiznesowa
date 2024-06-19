package com.example.projektJava.controller;

import com.example.projektJava.model.Category;
import com.example.projektJava.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public String getCategories(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories",categoryList);

        return "category/category-list";
    }

    @GetMapping("/category/add")
    public String addAdvert(Model model){

        model.addAttribute("category",new Category());

        return "category/add-category";
    }

    @PostMapping("/category/add")
    public String processAdvertForm(@ModelAttribute("category") Category category){
        categoryService.add(category);
        return "redirect:/category";
    }
    @GetMapping("/category/update/{id}")
    public String updateAdvert(Model model, @PathVariable Long id){

        model.addAttribute("category",categoryService.findById(id));

        return "category/edit-category";
    }

    @PostMapping("/category/update/{id}")
    public String updateAdvertForm(@ModelAttribute("category") Category category){
        categoryService.update(category);
        return "redirect:/category";
    }

    @PostMapping("/category/delete/{id}")
    public String deleteAdvert(@PathVariable Long id){
        categoryService.delete(categoryService.findById(id));
        return "redirect:/category";
    }
}
