package com.telran.phonebookapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    public void testAddPhoneNumber_contactWithoutPhoneNumber_onePhoneNumber() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("111111");

        contact.addPhoneNumber(phoneNumber);

        assertEquals("111111", contact.getNumbers().get(0).getNumber());
        assertEquals(1, contact.getNumbers().size());

    }

    @Test
    public void testAddAddress_contactWithoutAddress_oneAddress() {
        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        Address address = new Address();
        address.setAddress("Test street");

        contact.addAddress(address);

        assertEquals("Test street", contact.getAddresses().get(0).getAddress());
        assertEquals(1, contact.getAddresses().size());

    }

}
