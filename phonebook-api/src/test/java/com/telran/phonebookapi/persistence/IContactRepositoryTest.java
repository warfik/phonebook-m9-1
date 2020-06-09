package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.Contact;
import com.telran.phonebookapi.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IContactRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IContactRepository contactRepository;

    @Test
    public void testFindAllContacts_noContactsExistInTheList_emptyList() {
        List<Contact> foundedContacts = contactRepository.findAll();
        assertEquals(0, foundedContacts.size());
    }

    @Test
    public void testFindContactByName_oneRecord_foundContact() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();

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

        List<Contact> foundedContactsFromDB = contactRepository.findByLastName("ContactLastName");
        assertEquals(1, foundedContactsFromDB.size());

        assertEquals("ContactLastName", foundedContactsFromDB.get(0).getLastName());
    }

    @Test
    public void testRemoveContactByLastName_oneRecord_emptyList() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        contact.setLastName("ContactLastName");

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();

        contactRepository.removeContactByLastName("ContactLastName");
        List<Contact> foundedContactsFromDB = contactRepository.findAll();
        assertEquals(0, foundedContactsFromDB.size());

    }
}
