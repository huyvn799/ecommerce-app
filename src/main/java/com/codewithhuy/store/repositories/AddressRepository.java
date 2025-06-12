package com.codewithhuy.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codewithhuy.store.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}