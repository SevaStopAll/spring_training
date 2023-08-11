package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.entity.Role;
import com.sevastopall.spring.entity.User;
import com.sevastopall.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


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

    @Test
    void checkFirstTop() {
        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkTop3() {
        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), Sort.by("id").descending());
        org.assertj.core.api.Assertions.assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkSort() {
        Sort.TypedSort<User> sortBy = Sort.sort(User.class);
        Sort sortByNames = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));
        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortByNames.descending());
        org.assertj.core.api.Assertions.assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkPageable() {
        PageRequest pageable = PageRequest.of(0, 2, Sort.sort(User.class).by(User::getId));
        Page<User> slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getId()));

        while(slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getId()));
        }
    }

    @Test
    void checkPageableWithEntityGraph() {
        PageRequest pageable = PageRequest.of(0, 2, Sort.sort(User.class).by(User::getId));
        Page<User> slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while(slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
        /*org.assertj.core.api.Assertions.assertThat(slice).hasSize(2);*/
    }
}