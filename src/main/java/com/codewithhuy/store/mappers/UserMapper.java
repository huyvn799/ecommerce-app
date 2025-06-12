package com.codewithhuy.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.codewithhuy.store.dtos.ResgisterUserRequest;
import com.codewithhuy.store.dtos.UpdateUserRequest;
import com.codewithhuy.store.dtos.UserDto;
import com.codewithhuy.store.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    User toEntity(ResgisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);     
}
