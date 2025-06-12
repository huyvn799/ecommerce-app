package com.codewithhuy.store.mappers;

import com.codewithhuy.store.dtos.ResgisterUserRequest;
import com.codewithhuy.store.dtos.UpdateUserRequest;
import com.codewithhuy.store.dtos.UserDto;
import com.codewithhuy.store.entities.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-12T13:37:58+0700",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;
        Long id = null;
        String name = null;

        email = user.getEmail();
        id = user.getId();
        name = user.getName();

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

    @Override
    public void update(UpdateUserRequest request, User user) {
        if ( request == null ) {
            return;
        }

        user.setEmail( request.getEmail() );
        user.setName( request.getName() );
    }
}
