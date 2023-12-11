package com.engineering.web.bobo.repositories;

import com.engineering.web.bobo.models.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepositoryImpl implements ContactRepository {
    private final SessionFactory sessionFactory;

    public ContactRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Contact contact) {
        try(Session session = sessionFactory.openSession()){
            session.save(contact);
        }
    }
}
