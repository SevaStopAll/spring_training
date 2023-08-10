package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.entity.User;
import com.sevastopall.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a","ov");
        org.assertj.core.api.Assertions.assertThat(users).hasSize(3);
    }
}