package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IUserRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IUserRepository userRepository;

    @Test
    public void testFindUserByEmail_oneRecord_foundUser() {
        User user = new User("email", "password");

        entityManager.persist(user);
        entityManager.flush();
        entityManager.clear();

        List<User> foundedUsersFromDB = userRepository.findByEmail("email");
        assertEquals(1, foundedUsersFromDB.size());

        assertEquals("email", foundedUsersFromDB.get(0).getEmail());
    }

}
