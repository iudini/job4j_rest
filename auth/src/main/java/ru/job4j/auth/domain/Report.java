package ru.job4j.auth.domain;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.auth.model.Person;

import java.sql.Timestamp;

@Getter
@Setter
public class Report {
    private Long id;
    private String name;
    private Timestamp created;
    private Person person;

    public static Report of(Long id, String name, Person person) {
        Report r = new Report();
        r.id = id;
        r.name = name;
        r.person = person;
        r.created = new Timestamp(System.currentTimeMillis());
        return r;
    }
}
