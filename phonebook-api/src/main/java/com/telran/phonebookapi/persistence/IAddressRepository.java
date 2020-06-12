package com.telran.phonebookapi.persistence;

import com.telran.phonebookapi.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<Address, Integer> {

}
