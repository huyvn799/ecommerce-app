package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository<P> extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = "category")
    List<Product> findByCategoryId(Byte categoryId);

    @EntityGraph(attributePaths = "category") //bổ sung entity graph để hibernate thực hiện select join
    @Query("SELECT p FROM Product p")
    List<Product> findAllWithCategory();
}