package ru.itis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomerForm {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Integer age;
}
