package com.sevastopall.spring.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PersonalInfo2 {
    String getFirstName();

    String getLastName();

    String getBirthDate();

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();
}
