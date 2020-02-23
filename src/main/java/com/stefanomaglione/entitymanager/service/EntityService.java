package com.stefanomaglione.entitymanager.service;

import com.stefanomaglione.entitymanager.model.Share;

import java.util.Collection;

public interface EntityService<T> {

    T get(long id);

    T save(T t);

    T update(long id, T t);

    void delete(long id);

    Share saveShare(long id, Share share);

    Collection<Share> getShares(long id);
}
