package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.ResgisterUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-12T04:08:44+0700",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;

        id = user.getId();
        name = user.getName();
        email = user.getEmail();

        LocalDateTime createdAt = java.time.LocalDateTime.now();

        UserDto userDto = new UserDto( id, name, email, createdAt );

        return userDto;
    }

    @Override
    public User toEntity(ResgisterUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( request.getEmail() );
        user.name( request.getName() );
        user.password( request.getPassword() );

        return user.build();
    }
}
