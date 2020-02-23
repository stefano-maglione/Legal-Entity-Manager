package com.stefanomaglione.entitymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefanomaglione.entitymanager.model.Entity;
import com.stefanomaglione.entitymanager.model.Share;
import com.stefanomaglione.entitymanager.service.EntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EntityController.class)
class EntityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EntityService<Entity> entityService;


    @Test
    public void givenId_whenGetEntity_thenReturnJsonArray() throws Exception {


        Entity entity = new Entity("Sony", "CH", "2020-01-10", 3);

        given(entityService.get(1)).willReturn(entity);

        mvc.perform(get("/entity/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(entity.getName())))
                .andExpect(jsonPath("$.country", is(entity.getCountry())))
                .andExpect(jsonPath("$.incorporationDate", is(entity.getIncorporationDate())))
                .andExpect(jsonPath("$.maxShares", is(entity.getMaxShares())));

    }

    @Test
    public void givenEntity_whenAddEntity_thenReturnJsonArray() throws Exception {

        Entity entity = new Entity("Sony", "CH", "2020-01-10", 3);

        given(entityService.save(entity)).willReturn(entity);
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(entity);

        mvc.perform(post("/entity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(entity.getName())))
                .andExpect(jsonPath("$.country", is(entity.getCountry())))
                .andExpect(jsonPath("$.incorporationDate", is(entity.getIncorporationDate())))
                .andExpect(jsonPath("$.maxShares", is(entity.getMaxShares())));

    }

    @Test
    public void givenId_whenDeleteEntity_thenStatusOk() throws Exception {

        mvc.perform(delete("/entity/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }


    @Test
    public void givenEntity_whenUpdateEntity_thenReturnJsonArray() throws Exception {

        Entity entity = new Entity("Sony", "CH", "2020-01-10", 3);

        given(entityService.update(1, entity)).willReturn(entity);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(entity);

        mvc.perform(put("/entity/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(entity.getName())))
                .andExpect(jsonPath("$.country", is(entity.getCountry())))
                .andExpect(jsonPath("$.incorporationDate", is(entity.getIncorporationDate())))
                .andExpect(jsonPath("$.maxShares", is(entity.getMaxShares())));
    }


    @Test
    public void givenIdAndShare_whenAddShare_thenReturnJsonArray() throws Exception {

        Share share = new Share(1, "ShareName", 5);

        given(entityService.saveShare(1, share)).willReturn(share);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(share);

        mvc.perform(patch("/entity/1/shareholder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(share.getName())))
                .andExpect(jsonPath("$.quote", is(share.getQuote())));
    }

    @Test
    public void givenId_whenGetShare_thenReturnJsonArray() throws Exception {

        Share share = new Share(1L, "ShareName", 5);

        List<Share> shares = Arrays.asList(share);

        given(entityService.getShares(1)).willReturn(shares);

        mvc.perform(get("/entity/1/shareholder")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(share.getName())))
                .andExpect(jsonPath("$[0].quote", is(share.getQuote())));


    }

}