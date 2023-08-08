package com.sevastopall.spring.service;

import com.sevastopall.spring.database.repository.CrudRepository;
import com.sevastopall.spring.dto.CompanyReadDto;
import com.sevastopall.spring.entity.Company;
import com.sevastopall.spring.listener.entity.AccessType;
import com.sevastopall.spring.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId());
                });
    }
}
