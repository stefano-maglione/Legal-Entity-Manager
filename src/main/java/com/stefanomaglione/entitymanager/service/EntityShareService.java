package com.stefanomaglione.entitymanager.service;

import com.stefanomaglione.entitymanager.model.Share;

import java.util.Collection;

public interface EntityShareService<T> {

    T get(long id);

    Collection<T> getAll(long id);

    T save(T t);

    T update(long id, T t);

    void delete(long id);

}
