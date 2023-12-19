package com.engineering.web.bobo.services;

import com.engineering.web.bobo.models.Contact;
import com.engineering.web.bobo.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class ContactServiceImpl implements ContactService{
    private final JavaMailSender mailSender;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, JavaMailSender mailSender) {
        this.contactRepository = contactRepository;
        this.mailSender = mailSender;
    }

    @Override
    public void create(Contact contact) throws MessagingException, UnsupportedEncodingException {
        contactRepository.create(contact);
    }

    @Override
    public void sendEmail(String userEmail, String userName, String userSubject, String userMessage) throws MessagingException, UnsupportedEncodingException {
        String senderName = "Bobo Team";
        String content = "Здрасти,<br />"
                + "Следното съобщение бе изпратено от [[userName]] с поща за обратна връзка [[userEmail]] :<br />"
                + "Тема: "
                + userSubject + "<br />"
                + "Съдържание: "
                + userMessage;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(userEmail, senderName);
        helper.setTo("bobo.engineering@gmail.com");
//        Test mail address
//        helper.setTo("minus273dot15@gmail.com");
        helper.setSubject("Bobo Engineering Site Contact Form: " + userSubject);

        content = content.replace("[[userName]]", userName);
        content = content.replace("[[userEmail]]", userEmail);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
