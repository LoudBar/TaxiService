package ru.itis.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Driver {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Long avatarId;
}
