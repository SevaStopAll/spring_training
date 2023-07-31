package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.bpp.InjectBean;
import com.sevastopall.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;

}
