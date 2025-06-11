package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.ProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository<Product> productRepository;
    private final ProductMapper productMapper;

    @GetMapping()
    public List<ProductDto> getAllProducts(
        // fix tính năng sort theo price asc or desc
            // @RequestParam(required = false, defaultValue = "", name = "sort")String sortBy,
            @RequestParam(required = false, name = "categoryId") Byte categoryId
    ) {
        // fix tính năng sort theo price
        // if (!Set.of("name", "price").contains(sortBy)) {
        //     sortBy = "price";
        // }

        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productMapper.toDto(product));
        
    }
}
