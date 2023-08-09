package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface CompanyRepository extends CrudRepository<Company, Integer> {

     Optional<Company> findById(Integer id);

     void delete(Company entity);
}