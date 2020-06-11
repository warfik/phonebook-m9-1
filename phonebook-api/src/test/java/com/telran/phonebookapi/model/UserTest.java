package com.telran.phonebookapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testAddContact_userWithoutContacts_oneContact() {
        User user = new User("email", "password");
        Contact contact = new Contact();
        contact.setName("ContactName");

        user.addContact(contact);

        assertEquals("ContactName", contact.getName());
        assertEquals(1, user.getContacts().size());

    }
}
