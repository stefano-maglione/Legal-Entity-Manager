package com.stefanomaglione.entitymanager.dao;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    T save(T t);

    T update(T t);

    void delete(T t);

    Collection<T> getAll(long id);
}
