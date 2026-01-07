package com.pl.stocktradingplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequestDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotNull
    private Double balance;
}
