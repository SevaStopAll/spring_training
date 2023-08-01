package com.sevastopall.spring.service;

import com.sevastopall.spring.database.repository.CompanyRepository;
import com.sevastopall.spring.database.repository.CrudRepository;
import com.sevastopall.spring.database.repository.UserRepository;
import com.sevastopall.spring.entity.Company;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

}
