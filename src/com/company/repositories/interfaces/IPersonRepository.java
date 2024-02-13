package com.company.repositories.interfaces;

import com.company.models.Person;

import java.util.List;

public interface IPersonRepository {
    boolean createPerson(Person Person);
    Person getPerson(int id);
    List<Person> getAllPersons();
}
