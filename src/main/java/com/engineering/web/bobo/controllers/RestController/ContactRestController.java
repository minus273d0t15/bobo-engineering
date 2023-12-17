package com.engineering.web.bobo.controllers.RestController;

import com.engineering.web.bobo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
public class ContactRestController {

    @Autowired
    private ContactService emailService;

    @PostMapping("/contact")
    public ModelAndView  sendEmail(@RequestParam String email,
                            @RequestParam String name,
                            @RequestParam String subject,
                            @RequestParam String message) throws MessagingException, UnsupportedEncodingException {
        emailService.sendEmail(email, name, subject, message);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact_success");
        return modelAndView;
    }
}
