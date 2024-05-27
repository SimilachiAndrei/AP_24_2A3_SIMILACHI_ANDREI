package com.Server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";  // This returns the index.html file
    }

    @GetMapping("/about")
    public String about() {
        return "about";  // This returns the about.html file
    }
}
