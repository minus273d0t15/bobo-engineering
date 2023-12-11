package com.engineering.web.bobo.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

public class Contact {
    @Email
    String email;

    public Contact() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
