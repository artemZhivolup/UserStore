package ua.zhivolup.dao;

import ua.zhivolup.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsRepository {

    private List<Person> persons;
    private static PersonsRepository repository;

    private PersonsRepository(){
        persons = new ArrayList<>();
    }

    public static PersonsRepository getRepository(){
        if (repository == null){
            repository = new PersonsRepository();
        }
        return repository;
    }

    public void add(Person person) {
        persons.add(person);
    }

    public void add(List<Person> personsToAdd){
        persons.addAll(personsToAdd);
    }

    public List<Person> getAll() {
        return persons;
    }

    public void clear() {
        persons.clear();
    }
}
