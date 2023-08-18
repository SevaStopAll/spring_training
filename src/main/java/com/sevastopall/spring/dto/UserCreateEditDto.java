package com.sevastopall.spring.dto;

import com.sevastopall.spring.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    @Email
    String username;

/*    @DateTimeFormat(pattern = "yyyy-MM-dd")*/
    LocalDate birthDate;

    @NotBlank
            @Size(min = 3, max = 64)
    String firstname;

    @NotBlank
    String lastname;

    @NotNull
    Role role;

    @NotNull
    Integer companyId;
}
