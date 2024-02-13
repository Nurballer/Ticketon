package com.company.controllers;

import com.company.models.Person;
import com.company.repositories.interfaces.IPersonRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonController {
    private final IPersonRepository repo;

    public PersonController(IPersonRepository repo) {
        this.repo = repo;
    }

    public boolean createPerson(String name, String age, String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        boolean created = ValidateEmail(email); // Call the ValidateEmail method and assign the result to 'created'

        return Boolean.parseBoolean(String.valueOf(created));
    }

    // Move the ValidateEmail method outside of createPerson method
    public static boolean ValidateEmail(String email) {
        Pattern pattern = null;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getPerson(int id) {
        Person person = repo.getPerson(id);

        return (person == null ? "Person was not found!" : person.toString());
    }

    public String getAllPersons() {
        List<Person> persons = repo.getAllPersons();

        StringBuilder response = new StringBuilder();
        for (Person person : persons) {
            response.append(person.toString()).append("\n");
        }

        return response.toString();
    }
}
