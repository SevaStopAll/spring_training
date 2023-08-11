package com.sevastopall.spring.database.repository;

import com.sevastopall.spring.dto.UserFilter;
import com.sevastopall.spring.entity.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);


        Root<User> user = criteria.from(User.class);
        criteria.select(user);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.firstname() != null) {
            predicates.add(criteriaBuilder.like(user.get("firstname"), filter.firstname()));
        }
        if (filter.lastname() != null) {
            predicates.add(criteriaBuilder.like(user.get("lastname"), filter.lastname()));
        }
        if (filter.birthDate() != null) {
            predicates.add(criteriaBuilder.lessThan(user.get("birthDate"), filter.birthDate()));
        }
        criteria.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }

}
