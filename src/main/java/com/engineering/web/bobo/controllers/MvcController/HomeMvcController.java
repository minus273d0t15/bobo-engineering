package com.engineering.web.bobo.controllers.MvcController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMvcController {

    @GetMapping
    public String showHomePage(){
        return "index";
    }

    @GetMapping("who_are_we")
        public String whoAreWe(){
            return "who_are_we";
        }
    }

