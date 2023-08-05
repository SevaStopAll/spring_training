package com.sevastopall.spring;

import com.sevastopall.spring.config.ApplicationConfiguration;
import com.sevastopall.spring.database.pool.ConnectionPool;
import com.sevastopall.spring.database.repository.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ApplicationRunner {
    public static void main(String[] args) {
        CrudRepository companyRepository;
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();

            ConnectionPool pool = context.getBean("pool2", ConnectionPool.class);
            System.out.println(pool);
            companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
