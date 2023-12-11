package com.engineering.web.bobo.services;

import com.engineering.web.bobo.models.Contact;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ContactService {
    void create(Contact contact) throws MessagingException, UnsupportedEncodingException;

    void sendTestEmail(String senderUserEmail) throws MessagingException, UnsupportedEncodingException;
}
