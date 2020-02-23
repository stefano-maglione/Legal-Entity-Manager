package com.stefanomaglione.entitymanager.controller;

import com.stefanomaglione.entitymanager.model.Entity;
import com.stefanomaglione.entitymanager.model.Share;
import com.stefanomaglione.entitymanager.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/entity")
public class EntityController {

    @Autowired
    private EntityService<Entity> entityService;

    @GetMapping("/{id}")
    public Entity getEntity(@PathVariable Long id) {

        return entityService.get(id);

    }

    @PostMapping
    public Entity addEntity(@Valid @RequestBody Entity entity) {

        return entityService.save(entity);

    }

    @PutMapping("/{id}")
    public Entity updateEntity(@PathVariable Long id, @Valid @RequestBody Entity newEntity) {

        return entityService.update(id, newEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEntity(@PathVariable Long id) {

        entityService.delete(id);
    }

    @PatchMapping("/{id}/shareholder")
    public Share addShare(@PathVariable Long id, @Valid @RequestBody Share share) {

        return entityService.saveShare(id, share);

    }

    @GetMapping("/{id}/shareholder")
    public Collection<Share> getShare(@PathVariable Long id) {

        return entityService.getShares(id);

    }

}
