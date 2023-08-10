package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.entity.Role;
import com.sevastopall.spring.entity.User;
import com.sevastopall.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a","ov");
        org.assertj.core.api.Assertions.assertThat(users).hasSize(3);
    }

    @Test
    void checkUpdate() {
        User ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User sameIvan = userRepository.getById(1L);
        assertSame(Role.USER, sameIvan.getRole());
    }
}