package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.Contact;
import com.telran.phonebookapi.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IContactRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IContactRepository contactRepository;

    @Test
    public void testFindContactByName_oneRecord_foundContact() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        List<Contact> foundedContactsFromDB = contactRepository.findByName("ContactName");
        assertEquals(1, foundedContactsFromDB.size());

        assertEquals("ContactName", foundedContactsFromDB.get(0).getName());
    }

    @Test
    public void testFindContactByLastName_oneRecord_foundContact() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        contact.setLastName("ContactLastName");

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        List<Contact> foundedContactsFromDB = contactRepository.findByLastName("ContactLastName");
        assertEquals(1, foundedContactsFromDB.size());

        assertEquals("ContactLastName", foundedContactsFromDB.get(0).getLastName());
    }

    @Test
    public void testDeleteContactByLastName_oneRecord_emptyList() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        contact.setLastName("ContactLastName");

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        contactRepository.deleteContactByLastName("ContactLastName");
        List<Contact> foundedContactsFromDB = new ArrayList<>();
        contactRepository.findAll().forEach(foundedContactsFromDB::add);
        assertEquals(0, foundedContactsFromDB.size());

    }
}
