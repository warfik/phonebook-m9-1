package com.telran.phonebookapi.model;

import com.telran.phonebookapi.persistence.IContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContactTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IContactRepository contactRepository;

    @Test
    public void testAddPhoneNumber_contactWithoutPhoneNumber_onePhoneNumber() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("111111");

        contact.addPhoneNumber(phoneNumber);

        entityManager.clear();
        assertEquals("111111", contact.getNumbers().get(0).getNumber());
        assertEquals(1, contact.getNumbers().size());

    }

    @Test
    public void testAddAddress_contactWithoutAddress_oneAddress() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();
        Address address = new Address();
        address.setAddress("Test street");

        contact.addAddress(address);

        entityManager.clear();
        assertEquals("Test street", contact.getAddresses().get(0).getAddress());
        assertEquals(1, contact.getAddresses().size());

    }

}
