package com.codewithhuy.store.dtos;

import lombok.Data;

@Data
public class ResgisterUserRequest {
    private String name;
    private String email;
    private String password;
}
