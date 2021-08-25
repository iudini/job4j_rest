package ru.job4j.auth.domain;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.auth.model.Person;

import java.util.Date;

@Getter
@Setter
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String inn;
    private Date hiredDate;
    private Person person;

    public static Employee of(Long id, String firstName, String lastName, String inn, Person person) {
        Employee employee = new Employee();
        employee.id = id;
        employee.firstName = firstName;
        employee.lastName = lastName;
        employee.inn = inn;
        employee.hiredDate = new Date();
        employee.person = person;
        return employee;
    }
}
