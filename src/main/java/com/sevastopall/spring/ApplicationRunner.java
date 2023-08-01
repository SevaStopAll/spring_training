package com.sevastopall.spring;

import com.sevastopall.spring.database.pool.ConnectionPool;
import com.sevastopall.spring.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        CrudRepository companyRepository;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {
            ConnectionPool pool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool);
            companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
