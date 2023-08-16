package com.sevastopall.spring.integration.service;

import com.sevastopall.spring.database.pool.ConnectionPool;
import com.sevastopall.spring.dto.UserCreateEditDto;
import com.sevastopall.spring.dto.UserReadDto;
import com.sevastopall.spring.entity.Role;
import com.sevastopall.spring.integration.IntegrationTestBase;
import com.sevastopall.spring.integration.annotation.IT;
import com.sevastopall.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RequiredArgsConstructor
/*@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)*/
public class UserServiceTestIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_ID = 1;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        org.junit.jupiter.api.Assertions.assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> org.junit.jupiter.api.Assertions.assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_ID
        );
        UserReadDto actualResult = userService.create(userDto);
        org.junit.jupiter.api.Assertions.assertEquals(userDto.getUsername(), actualResult.getUsername());
        org.junit.jupiter.api.Assertions.assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        org.junit.jupiter.api.Assertions.assertEquals(userDto.getLastname(), actualResult.getLastname());
        org.junit.jupiter.api.Assertions.assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
        org.junit.jupiter.api.Assertions.assertSame(userDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_ID
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);
        org.junit.jupiter.api.Assertions.assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            org.junit.jupiter.api.Assertions.assertEquals(userDto.getUsername(), user.getUsername());
            org.junit.jupiter.api.Assertions.assertEquals(userDto.getFirstname(), user.getFirstname());
            org.junit.jupiter.api.Assertions.assertEquals(userDto.getLastname(), user.getLastname());
            org.junit.jupiter.api.Assertions.assertEquals(userDto.getCompanyId(), user.getCompany().id());
            org.junit.jupiter.api.Assertions.assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        org.junit.jupiter.api.Assertions.assertFalse(userService.delete(30L));
        org.junit.jupiter.api.Assertions.assertTrue(userService.delete(USER_1));
    }
}
