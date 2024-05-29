package com.Server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";  // This returns the index.html file
    }

    @GetMapping("/statistics")
    public String statistics() {
        return "statistics";  // This returns the index.html file
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule";  // This returns the index.html file
    }

}
