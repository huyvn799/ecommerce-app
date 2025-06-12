package com.codewithhuy.store.controllers;

import lombok.AllArgsConstructor;
import lombok.experimental.var;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewithhuy.store.dtos.ProductDto;
import com.codewithhuy.store.entities.Product;
import com.codewithhuy.store.mappers.ProductMapper;
import com.codewithhuy.store.repositories.CategoryRepository;
import com.codewithhuy.store.repositories.ProductRepository;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository<Product> productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

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

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
        @RequestBody ProductDto productDto,
        UriComponentsBuilder uriBuilder    
    ) {
        // vì category của ProductDto là Id, ko map được Category của Product là Class
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(productDto);
        // Set category cho product
        product.setCategory(category);
        productRepository.save(product);

        // productDto thiếu Id nên bổ sung Id và response nó luôn
        productDto.setId(product.getId());
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
        @PathVariable(name = "id") Long id,
        @RequestBody ProductDto productDto
    ) {
        // lấy category từ request -> update category
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productMapper.update(productDto, product);
        product.setCategory(category); //cập nhật category thay vì categoryId (vì entity Product chứa Category)
        productRepository.save(product);
        productDto.setId(product.getId()); //Thêm id cho productDto
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
