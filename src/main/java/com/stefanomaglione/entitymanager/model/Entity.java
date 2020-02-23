package com.stefanomaglione.entitymanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stefanomaglione.entitymanager.exception.validator.Country;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Component
public class Entity {

    private long id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    @NotNull(message = "Please provide an incorporation date")
    private String incorporationDate;

    @NotEmpty(message = "Please provide a country")
    @Country
    private String country;

    @NotNull(message = "Please provide a max number of shares")
    private Integer maxShares;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int soldShares;

    public Entity() {
    }

    public Entity(String name, String country, String incorporationDate, Integer maxShares) {
        this.name = name;
        this.incorporationDate = incorporationDate;
        this.country = country;
        this.maxShares = maxShares;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncorporationDate() {
        return incorporationDate;
    }

    public void setIncorporationDate(String incorporationDate) {
        this.incorporationDate = incorporationDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getMaxShares() {
        return maxShares;
    }

    public void setMaxShares(Integer maxShares) {
        this.maxShares = maxShares;
    }

    public int getSoldShares() {
        return soldShares;
    }

    public void setSoldShares(int soldShares) {
        this.soldShares = soldShares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
