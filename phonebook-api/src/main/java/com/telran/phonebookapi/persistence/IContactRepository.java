package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IContactRepository extends CrudRepository<Contact, Integer> {

    public List<Contact> findByName(String name);

    public List<Contact> findByLastName(String lastName);

}
