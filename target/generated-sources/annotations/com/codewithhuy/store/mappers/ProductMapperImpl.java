package com.codewithhuy.store.mappers;

import com.codewithhuy.store.dtos.ProductDto;
import com.codewithhuy.store.entities.Category;
import com.codewithhuy.store.entities.Product;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-12T13:33:48+0700",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long arg0 = null;
        String arg1 = null;
        String arg2 = null;
        BigDecimal arg3 = null;
        Byte arg4 = null;

        ProductDto productDto = new ProductDto( arg0, arg1, arg2, arg3, arg4 );

        productDto.setCategoryId( productCategoryId( product ) );
        productDto.setDescription( product.getDescription() );
        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );

        return productDto;
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.description( productDto.getDescription() );
        product.id( productDto.getId() );
        product.name( productDto.getName() );
        product.price( productDto.getPrice() );

        return product.build();
    }

    @Override
    public void update(ProductDto productDto, Product product) {
        if ( productDto == null ) {
            return;
        }

        product.setDescription( productDto.getDescription() );
        product.setName( productDto.getName() );
        product.setPrice( productDto.getPrice() );
    }

    private Byte productCategoryId(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }
}
