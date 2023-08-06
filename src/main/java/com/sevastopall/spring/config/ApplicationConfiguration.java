package com.sevastopall.spring.config;

import com.sevastopall.spring.database.pool.ConnectionPool;
import com.sevastopall.spring.database.repository.UserRepository;
import com.sevastopall.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;


@Import(WebConfiguration.class)
@Configuration()
public class ApplicationConfiguration {

    @Bean("pool2")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 24);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("testPool", 25);
    }

    @Bean
    @Profile("prod")
    // ! | &
    public UserRepository userRepository2(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3() {
        ConnectionPool connectionPool = pool3();
        ConnectionPool connectionPool2 = pool3();
        ConnectionPool connectionPool3 = pool3();
        return new UserRepository(pool3());
    }

}
