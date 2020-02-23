package com.stefanomaglione.entitymanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Component
public class Share {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long id;

    @NotNull(message = "Please provide an holder id")
    private long holderId;

    @NotNull(message = "Please provide a share name")
    private String name;

    @NotNull(message = "Please provide a quote")
    private int quote;

    private long sellerId;

    public Share() {
    }


    public Share(long holderId, String name, int quote) {

        this.holderId = holderId;
        this.name = name;
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHolderId() {
        return holderId;
    }

    public void setHolderId(long holderId) {
        this.holderId = holderId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long seller) {
        this.sellerId = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Share share = (Share) o;
        return id == share.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
