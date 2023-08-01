package com.sevastopall.spring.database.repository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CrudRepository <K, E> {
    Optional<E> findById(K id);

    void delete(E entity);
}
