package com.example.vip_rent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String identityNumber;
}
