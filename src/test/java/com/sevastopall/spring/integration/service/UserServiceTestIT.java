package com.sevastopall.spring.integration.service;

import com.sevastopall.spring.database.pool.ConnectionPool;
import com.sevastopall.spring.integration.annotation.IT;
import com.sevastopall.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

@IT
@RequiredArgsConstructor
/*@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)*/
public class UserServiceTestIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test() {
        System.out.println();
    }

    @Test
    void test2() {
        System.out.println();
    }
}
