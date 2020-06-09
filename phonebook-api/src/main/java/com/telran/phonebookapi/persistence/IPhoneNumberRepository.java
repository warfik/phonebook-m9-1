package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPhoneNumberRepository extends CrudRepository<PhoneNumber, Integer> {

    public List<PhoneNumber> findByContactId(int id);

    public void removePhoneNumberByNumber(String number);

}
