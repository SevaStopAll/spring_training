package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.dto.PersonalInfo;
import com.sevastopall.spring.dto.UserFilter;
import com.sevastopall.spring.entity.Role;
import com.sevastopall.spring.entity.User;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
