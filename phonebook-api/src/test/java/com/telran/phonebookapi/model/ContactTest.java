package com.telran.phonebookapi.model;

import com.telran.phonebookapi.persistence.IAddressRepository;
import com.telran.phonebookapi.persistence.IPhoneNumberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContactTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IAddressRepository addressRepository;

    @Autowired
    IPhoneNumberRepository phoneNumberRepository;

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

        assertEquals("111111", contact.getNumbers().get(0).getNumber());
        assertEquals(1, contact.getNumbers().size());

        List<PhoneNumber> foundedPhoneNumbers = phoneNumberRepository.findByContact(contact);
        assertEquals("111111", foundedPhoneNumbers.get(0).getNumber());
        assertEquals(1, foundedPhoneNumbers.size());
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

        assertEquals("Test street", contact.getAddresses().get(0).getAddress());
        assertEquals(1, contact.getAddresses().size());

        List<Address> foundedAddresses = addressRepository.findByContact(contact);
        assertEquals("Test street", foundedAddresses.get(0).getAddress());
        assertEquals(1, foundedAddresses.size());
    }

}
