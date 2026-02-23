package com.yasaruniversity.mywebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/calculator"})
    public String index(Model model) {
        model.addAttribute("title", "Basit Hesap Makinesi");
        return "index"; // resolves to templates/index.html
        }
}
