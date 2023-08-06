package com.sevastopall.spring.service;

import com.sevastopall.spring.database.repository.CompanyRepository;
import com.sevastopall.spring.database.repository.CrudRepository;
import com.sevastopall.spring.database.repository.UserRepository;
import com.sevastopall.spring.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

}
