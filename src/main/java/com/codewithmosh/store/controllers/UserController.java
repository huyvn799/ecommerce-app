package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ResgisterUserRequest;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository<User> userRepository;
    private final UserMapper userMapper;

    @GetMapping()
    // method: GET
    public Iterable<UserDto> getAllUsers(
            // Sử dụng Request Header
            @RequestHeader(required = false, name = "x-auth-token") String authToken,
            // Lưu ý phải có những thuộc tính này khi khai báo param
            @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy
    ) {
        // In ra console x-auth-token
        System.out.println(authToken);
        // Nếu trong value của param sort ko phải name hoặc email thì trả về 1 giá trị hợp lệ
        if (!Set.of("name", "email").contains(sortBy)) {
            sortBy = "name";
        }
        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto) //cú pháp chuẩn: user -> userMapper.toDto(user
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user =  userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
        @RequestBody ResgisterUserRequest request,
        UriComponentsBuilder uriBuilder
    ){
        var user = userMapper.toEntity(request);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();

        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
        @PathVariable(name = "id") Long id,
        @RequestBody UpdateUserRequest request
    ) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userMapper.update(request, user);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id){
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            ResponseEntity.notFound().build();
        } else {
            userRepository.delete(user);
        }
        return ResponseEntity.noContent().build();
    }
}
