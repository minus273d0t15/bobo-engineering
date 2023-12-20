package com.engineering.web.bobo.controllers.MvcController;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeMvcController {
    //Home Page
    @GetMapping
    public String showHomePage() {
        return "index";
    }

    //Кои сме ние
    @GetMapping("who_are_we")
    public String whoAreWe() {
        return "who_are_we";
    }

    // Управление на проекти в строителството
    @GetMapping("управление-на-проекти-в-строителство")
    public String projectManagementInConstruction() {
        return "project_management_in_construction";
    }

    // Проектиране
    @GetMapping("engineering")
    public String engineering() {
        return "engineering";
    }

    //Консултантска-дейност
    @GetMapping("consultancy_services")
    public String consultancyServices() {
        return "consultancy_services";
    }

    @GetMapping("/quality-control")
    public String getQualityControl() {
        // Implement your logic here
        return "construction_quality_control";
    }

    @GetMapping("portfolio/{projectID}")
    public String getByPhoneNumber(@PathVariable String projectID, Model model) {
        //Temporary solution; Get path by ID to images
        //Further migration to SQL queries expected/grep image path info by SQL, editable in admin panel/
        if (Integer.parseInt(projectID) > 2 || Integer.parseInt(projectID) < 1) return "NotFoundView";
        try {
            String resourcePath = "static/img/portfolio/project0" + projectID + "/";
            ClassPathResource imgDir = new ClassPathResource(resourcePath);

            if (imgDir.exists() && imgDir.getFile().isDirectory()) {
                File[] directoryListing = imgDir.getFile().listFiles();
                List<String> imagePaths = new ArrayList<>();

                if (directoryListing != null) {
                    for (File child : directoryListing) {
                        if (child.isFile() && child.getName().toLowerCase().endsWith(".jpg")) {
                            imagePaths.add("/img/portfolio/project0" + projectID + "/" + child.getName());
                        }
                    }
                }

                model.addAttribute("pictures", imagePaths);
                model.addAttribute("ID", projectID);
            }
            return "portfolio-details";
        } catch (IOException e) {
            return "NotFoundView";
        }


    }
}