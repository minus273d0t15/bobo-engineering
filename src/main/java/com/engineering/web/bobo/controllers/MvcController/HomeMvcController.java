package com.engineering.web.bobo.controllers.MvcController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMvcController {
    //Home Page
    @GetMapping
    public String showHomePage(){
        return "index";
    }
        //Кои сме ние
    @GetMapping("who_are_we")
        public String whoAreWe(){
            return "who_are_we";
        }
        // Управление на проекти в строителството
       @GetMapping("управление-на-проекти-в-строителство")
            public String projectManagementInConstruction(){
            return "project_management_in_construction";
            }
            // Проектиране
            @GetMapping("engineering")
        public String engineering(){
        return "engineering";
            }
            //Консултантска-дейност
    @GetMapping("consultancy_services")
    public String consultancyServices(){
        return "consultancy_services";
    }
    @GetMapping("/quality-control")
    public String getQualityControl() {
        // Implement your logic here
        return "construction_quality_control";
    }
    }

