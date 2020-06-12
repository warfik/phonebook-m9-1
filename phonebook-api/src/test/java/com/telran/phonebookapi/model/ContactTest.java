package com.telran.phonebookapi.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContactTest {

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testAddPhoneNumber_contactWithoutPhoneNumber_onePhoneNumber() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("111111");

        contact.addPhoneNumber(phoneNumber);

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        PhoneNumber phoneNumberFromDB = entityManager.find(PhoneNumber.class, 1);
        assertEquals(phoneNumberFromDB.getNumber(), "111111");

    }

    @Test
    public void testAddAddress_contactWithoutAddress_oneAddress() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        Address address = new Address();
        address.setAddress("Test street");

        contact.addAddress(address);

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.flush();
        entityManager.clear();

        Address addressFromDB = entityManager.find(Address.class, 1);
        assertEquals(addressFromDB.getAddress(), "Test street");

    }

}
