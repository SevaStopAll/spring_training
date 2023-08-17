package com.sevastopall.spring.dto;

import com.sevastopall.spring.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    Integer companyId;
}
