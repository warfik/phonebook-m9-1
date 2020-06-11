package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.Contact;
import com.telran.phonebookapi.model.PhoneNumber;
import com.telran.phonebookapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IPhoneNumberRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IPhoneNumberRepository phoneNumberRepository;

    @Test
    public void testFindPhoneNumberByContact_contactWithPhoneNumberExistsInTheList_listWithOnePhoneNumber() {

        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        PhoneNumber phoneNumber = new PhoneNumber("111111", contact);

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.persist(phoneNumber);
        entityManager.flush();
        entityManager.clear();

        List<PhoneNumber> foundedPhoneNumbers = phoneNumberRepository.findByContact(contact);
        assertEquals(1, foundedPhoneNumbers.size());
        assertEquals("111111", foundedPhoneNumbers.get(0).getNumber());
    }

}
