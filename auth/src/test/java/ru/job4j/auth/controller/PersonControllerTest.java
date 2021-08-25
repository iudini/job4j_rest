package ru.job4j.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository persons;

    @Test
    void whenFindAllThenReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/person/"))
                .andExpect(status().isOk());
    }

    @Test
    void whenFindByIdThenReturnDefaultMessage() throws Exception {
        Person person = new Person(1L, "first", "password");
        when(persons.findById(anyLong())).thenReturn(Optional.of(person));
        this.mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("first"))
                .andExpect(jsonPath("$.password").value("password"));
    }

    @Test
    void whenNotFindByIdThenReturnStatus404() throws Exception {
        this.mockMvc.perform(get("/person/0"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void whenCreateThenReturnDefaultMessage() throws Exception {
        Person person = new Person(1L, "login", "password");
        this.mockMvc.perform(post("/person/")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void whenUpdateThenReturnDefaultMessage() throws Exception {
        Person person = new Person(1L, "login", "password");
        this.mockMvc.perform(put("/person/")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDeleteThenReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(delete("/person/1"))
                .andExpect(status().isOk());
    }
}