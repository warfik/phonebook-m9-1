package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.Address;
import com.telran.phonebookapi.model.Contact;
import com.telran.phonebookapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IAddressRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IAddressRepository addressRepository;

    @Test
    public void testFindAddressByContact_contactWithAddressExistsInTheList_listWithOneAddress() {

        User user = new User("email", "password");
        Contact contact = new Contact("ContactName", user);
        Address address = new Address("Test street 01", contact);

        entityManager.persist(user);
        entityManager.persist(contact);
        entityManager.persist(address);
        entityManager.flush();
        entityManager.clear();

        List<Address> foundedAddresses = addressRepository.findByContact(contact);
        assertEquals(1, foundedAddresses.size());
        assertEquals("Test street 01", foundedAddresses.get(0).getAddress());
    }

}
