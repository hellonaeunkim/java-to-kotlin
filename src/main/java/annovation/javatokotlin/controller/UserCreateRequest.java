package annovation.javatokotlin.controller;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String email;
}
