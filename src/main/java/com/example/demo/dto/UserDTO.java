package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String pk;
    private String username;
    private String password;
    private String passwordCheck;
    private Integer borrowedBooksCnt;
}
