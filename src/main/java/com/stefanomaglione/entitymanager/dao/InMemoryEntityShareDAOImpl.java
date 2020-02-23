package com.stefanomaglione.entitymanager.dao;

import com.stefanomaglione.entitymanager.model.Share;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@Qualifier("InMemoryEntityShare")
public class InMemoryEntityShareDAOImpl implements Dao<Share> {


    private MultiValuedMap<Long, Share> entityShare = new ArrayListValuedHashMap<>();

    @Override
    public Optional<Share> get(long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Share> getAll(long id) {
        return entityShare.get(id);
    }

    @Override
    public Share save(Share share) {
        entityShare.put(share.getSellerId(), share);

        return share;
    }

    @Override
    public Share update(Share share) {
        return null;
    }

    @Override
    public void delete(Share share) {

    }
}
