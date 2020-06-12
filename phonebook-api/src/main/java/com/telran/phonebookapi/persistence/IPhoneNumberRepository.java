package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

public interface IPhoneNumberRepository extends CrudRepository<PhoneNumber, Integer> {

}
