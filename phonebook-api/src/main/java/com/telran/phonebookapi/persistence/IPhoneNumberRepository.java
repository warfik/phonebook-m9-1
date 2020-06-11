package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.Contact;
import com.telran.phonebookapi.model.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPhoneNumberRepository extends CrudRepository<PhoneNumber, Integer> {

    public List<PhoneNumber> findByContact(Contact contact);

}
