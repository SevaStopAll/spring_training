package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
public interface CompanyRepository extends JpaRepository<Company, Integer> {

     ///Optional, Entity, Future
     //@Query(name = "Company.findByName")
     @Query(value = "select c from Company c " +
             "join fetch c.locales cl where c.name = :name")
     Optional<Company> findByName(String name);

     // Collection, Stream (batch, close)
     List<Company> findAllByNameContainingIgnoreCase(String fragment);
}