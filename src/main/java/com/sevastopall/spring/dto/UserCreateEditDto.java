package com.sevastopall.spring.dto;

import com.sevastopall.spring.entity.Role;
import com.sevastopall.spring.validation.UserInfo;
import com.sevastopall.spring.validation.group.CreateAction;
import com.sevastopall.spring.validation.group.UpdateAction;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.postgresql.util.LruCache;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = UpdateAction.class)
public class UserCreateEditDto {
    @Email
    String username;

/*    @DateTimeFormat(pattern = "yyyy-MM-dd")*/
    LocalDate birthDate;

    @Size(min = 3, max = 64)
    String firstname;

    String lastname;

    @NotNull
    Role role;

    @NotNull
    Integer companyId;

    MultipartFile image;
}
