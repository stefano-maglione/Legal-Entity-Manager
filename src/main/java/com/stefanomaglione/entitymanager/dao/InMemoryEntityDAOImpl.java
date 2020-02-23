package com.stefanomaglione.entitymanager.dao;

import com.stefanomaglione.entitymanager.model.Entity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class InMemoryEntityDAOImpl implements Dao<Entity> {

    private static final AtomicLong currentId = new AtomicLong(0L);
    private Map<Long, Entity> entities = new HashMap<>();


    @Override
    public Optional<Entity> get(long id) {

        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Entity save(Entity entity) {

        Long key = currentId.incrementAndGet();

        while (entities.containsKey(key)) {
            key = currentId.incrementAndGet();
        }

        entity.setId(key);
        entities.put(entity.getId(), entity);
        return entity;
    }


    @Override
    public Entity update(Entity entity) {

        entities.put(entity.getId(), entity);

        return entity;
    }

    @Override
    public void delete(Entity entity) {

        entities.remove(entity.getId());
    }

    @Override
    public Collection<Entity> getAll(long id) {
        return null;
    }
}
