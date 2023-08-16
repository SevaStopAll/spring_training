package com.sevastopall.spring.integration.service;


import com.sevastopall.spring.config.DatabaseProperties;
import com.sevastopall.spring.dto.CompanyReadDto;
import com.sevastopall.spring.integration.annotation.IT;
import com.sevastopall.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
/*@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)*/
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID, null);
        actualResult.ifPresent(actual ->  assertEquals(expectedResult, actual));
    }

}
