package com.codewithhuy.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.codewithhuy.store.entities.User;

public interface UserRepository<U> extends JpaRepository<User, Long> {
}
