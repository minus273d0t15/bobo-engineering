package com.engineering.web.bobo.controllers.MvcController;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        if (Integer.parseInt(projectID) > 3 || Integer.parseInt(projectID) < 1) return "NotFoundView";
        try {
            String resourcePath = "static/img/portfolio/project0" + projectID + "/";
            ClassPathResource imgDir = new ClassPathResource(resourcePath);

            if (imgDir.exists() && imgDir.getFile().isDirectory()) {
                File[] directoryListing = imgDir.getFile().listFiles();
                List<String> imagePaths = new ArrayList<>();

                if (directoryListing != null) {
                    for (File child : directoryListing) {
                        String fileName = child.getName().toLowerCase();
                        if (child.isFile() && fileName.endsWith(".jpg") && !fileName.contains("thumb")) {
                            imagePaths.add("/img/portfolio/project0" + projectID + "/" + child.getName());
                        }
                    }
                }

                model.addAttribute("pictures", imagePaths);
                model.addAttribute("ID", projectID);
            }

            //load txt content, same folder
            ClassPathResource projectInfoFile = new ClassPathResource(resourcePath + "project_info.txt");

            String projectInfoContent;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(projectInfoFile.getInputStream()))) {
                projectInfoContent = reader.lines().collect(Collectors.joining("\n"));
                Map<String, String> projectDetails = new HashMap<>();
                Pattern pattern = Pattern.compile("^(.*?):\\s*(.*?)$", Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(projectInfoContent);

                while (matcher.find()) {
                    projectDetails.put(matcher.group(1).trim(), matcher.group(2).trim());
                }
                model.addAttribute("projectDetails", projectDetails);
            } catch (IOException e) {
                return "NotFoundView";
            }
            return "portfolio-details";
        } catch (IOException e) {
            return "NotFoundView";
        }


    }
}