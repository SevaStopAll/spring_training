package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.dto.UserFilter;
import com.sevastopall.spring.entity.User;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
