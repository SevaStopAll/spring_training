package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.database.pool.ConnectionPool;
import com.sevastopall.spring.dto.PersonalInfo;
import com.sevastopall.spring.dto.PersonalInfo2;
import com.sevastopall.spring.entity.Role;
import com.sevastopall.spring.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Integer> {

    @Query(value = "select u from User u where u.firstname like %:firstname% AND u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long ... ids);

    Optional<User> findTopByOrderByIdDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u",
        countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

    // List<PersonalInfo> findAllByCompanyId(Integer companyId);

    /*<T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);*/
    @Query(value = "SELECT firstname," +
            " lastname, " +
            "birth_date birthDate " +
            "from users " +
            "WHERE company_id = :companyId",
            nativeQuery = true)
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);


}
