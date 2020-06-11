package com.telran.phonebookapi.model;

import com.telran.phonebookapi.persistence.IContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IContactRepository contactRepository;

    @Test
    public void testAddContact_userWithoutContacts_oneContact() {
        User user = new User("email", "password");
        Contact contact = new Contact();
        contact.setName("ContactName");

        user.addContact(contact);
        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        assertEquals("ContactName", contact.getName());
        assertEquals(1, user.getContacts().size());

    }
}
