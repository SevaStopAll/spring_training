package com.sevastopall.spring;

import com.sevastopall.spring.database.pool.ConnectionPool;
import com.sevastopall.spring.database.repository.CompanyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        CompanyRepository companyRepository;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {
            ConnectionPool pool = context.getBean("pool", ConnectionPool.class);
            System.out.println(pool);
            companyRepository = context.getBean("companyRepository", CompanyRepository.class);
            System.out.println(companyRepository);
        }
    }
}
