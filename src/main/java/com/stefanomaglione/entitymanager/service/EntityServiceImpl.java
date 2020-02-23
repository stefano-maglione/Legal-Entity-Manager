package com.stefanomaglione.entitymanager.service;

import com.stefanomaglione.entitymanager.dao.Dao;
import com.stefanomaglione.entitymanager.exception.EntityAlreadyExistsException;
import com.stefanomaglione.entitymanager.exception.EntityNotFoundException;
import com.stefanomaglione.entitymanager.exception.ExceededShareException;
import com.stefanomaglione.entitymanager.model.Entity;
import com.stefanomaglione.entitymanager.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EntityServiceImpl implements EntityService<Entity> {

    @Autowired
    private Dao<Entity> dao;

    @Autowired
    @Qualifier("EntityShareService")
    private EntityShareService<Share> entityShareService;


    @Override
    public Entity get(long id) {

        Optional<Entity> entity = dao.get(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("Entity not found with id: " + id);
    }

    @Override
    public Entity save(Entity newEntity) {

        Optional<Entity> entity = dao.get(newEntity.getId());
        if (entity.isPresent()) {
            throw new EntityAlreadyExistsException("Entity with id: " + newEntity.getId() + " already exists");
        }

        return dao.save(newEntity);
    }

    @Override
    public Entity update(long id, Entity newEntity) {

        return dao.get(id)
                .map(entity -> {
                    entity.setName(newEntity.getName());
                    entity.setIncorporationDate(newEntity.getIncorporationDate());
                    entity.setCountry(newEntity.getCountry());
                    entity.setMaxShares(newEntity.getMaxShares());
                    return dao.update(entity);
                })
                .orElseGet(() -> {
                    newEntity.setId(id);
                    return dao.update(newEntity);
                });
    }

    @Override
    public void delete(long id) {

        Optional<Entity> entity = dao.get(id);
        if (!entity.isPresent()) {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }
        dao.delete(entity.get());
    }

    @Override
    public Collection<Share> getShares(long id) {

        return entityShareService.getAll(id);
    }

    @Override
    public Share saveShare(long id, Share share) {

        Optional<Entity> entitySeller = dao.get(id);
        if (!entitySeller.isPresent()) {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }

        Optional<Entity> entityHolder = dao.get(share.getHolderId());
        if (!entityHolder.isPresent()) {
            throw new EntityNotFoundException("Entity not found with id: " + share.getHolderId());
        }


        share.setSellerId(id);

        Entity entity = entitySeller.get();
        Integer soldShares = entity.getSoldShares();
        Integer quotes = share.getQuote();
        Integer remain = entity.getMaxShares() - soldShares;

        if (quotes > remain) {
            throw new ExceededShareException("Entity with id: " + id + " has " + remain + " shares available");
        }

        entity.setSoldShares(soldShares + quotes);

        return entityShareService.save(share);

    }


}
