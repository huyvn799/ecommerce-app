package com.codewithhuy.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithhuy.store.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}