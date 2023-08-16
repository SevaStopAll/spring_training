package com.sevastopall.spring.mapper;

import com.sevastopall.spring.dto.CompanyReadDto;
import com.sevastopall.spring.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {

    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(
                object.getId(),
                object.getName()
        );
    }
}
