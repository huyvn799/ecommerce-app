package com.codewithhuy.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codewithhuy.store.entities.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}