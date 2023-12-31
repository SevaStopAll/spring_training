package com.sevastopall.spring.integration.repository;

import com.sevastopall.spring.database.repository.UserRepository;
import com.sevastopall.spring.dto.PersonalInfo;
import com.sevastopall.spring.dto.PersonalInfo2;
import com.sevastopall.spring.dto.UserFilter;
import com.sevastopall.spring.entity.Role;
import com.sevastopall.spring.entity.User;
import com.sevastopall.spring.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {
    private final UserRepository userRepository;

    @Test
    /*@Commit*/
    void checkAuditing() {
        User ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a","ov");
        assertThat(users).hasSize(3);
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
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkSort() {
        Sort.TypedSort<User> sortBy = Sort.sort(User.class);
        Sort sortByNames = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));
        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortByNames.descending());
        assertThat(allUsers).hasSize(3);
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

    @Test
    void checkProjections() {
        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
         System.out.println();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(
                null, "ov", LocalDate.now()
        );
        List<User> users = userRepository.findAllByFilter(filter);
        assertThat(users).hasSize(4);
    }

    @Test
    void checkJdbcTemplate() {
        List<PersonalInfo> users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
    }

    @Test
    void checkBatch() {
        List<User> users = userRepository.findAll();
        userRepository.updateCompanyAndRoleNamed(users);
        System.out.println();
    }
}