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
    public void sendEmail(String userEmail, String userSubject, String userMessage) throws MessagingException, UnsupportedEncodingException {
        String fromAddress = "bobo.test@gmail.com";
        String senderName = "Bobo Team";
        String subject = "Bobo - test mail";
        String content = "Dear User,<br>"
                + "This is a test mail from [[senderUserEmail]]"
                + "Thank you,<br>"
                + "Bobo Team";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(userEmail);
        helper.setSubject(subject);

        content = content.replace("[[senderUserEmail]]", "tester");
        helper.setText(content, true);
        mailSender.send(message);
    }
}
