package com.stefanomaglione.entitymanager.service;

import com.stefanomaglione.entitymanager.dao.Dao;
import com.stefanomaglione.entitymanager.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Qualifier("EntityShareService")
public class EntityShareServiceImpl implements EntityShareService<Share> {

    @Autowired
    @Qualifier("InMemoryEntityShare")
    private Dao<Share> dao;

    @Override
    public Share get(long id) {
        return null;
    }

    @Override
    public Collection<Share> getAll(long id) {
        return dao.getAll(id);
    }

    @Override
    public Share save(Share share) {

        return dao.save(share);
    }

    @Override
    public Share update(long id, Share share) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
