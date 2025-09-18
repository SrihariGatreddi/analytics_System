package com.website.analytics_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:/home.html";
    }

    @GetMapping("/about")
    public String about() {
        return "redirect:/about.html";
    }

    @GetMapping("/contact")
    public String contact() {
        return "redirect:/contact.html";
    }
}
