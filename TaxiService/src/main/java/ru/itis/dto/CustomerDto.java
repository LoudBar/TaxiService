package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.models.Customer;

@Data
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private Long avatarId;

    public static CustomerDto form(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .age(customer.getAge())
                .avatarId(customer.getAvatarId())
                .build();
    }
}
