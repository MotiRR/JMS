package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Filler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private Filler filler;

    @GetMapping
    public String showAdminPage(Model model) {
        model.addAttribute("services", filler.fillMapForSumAspect());
        return "admin-page";
    }
}
