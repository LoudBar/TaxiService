package ru.itis.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private int age;
    private Long avatarId;
}
