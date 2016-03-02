package ua.zhivolup.service;

import ua.zhivolup.dao.PersonDao;
import ua.zhivolup.dao.PersonsRepository;
import ua.zhivolup.entity.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonService {
    private PersonDao personDao;

    public void setPersonDao(PersonDao userDao) {
        this.personDao = userDao;
    }

    public void save(){
        List<Person> allUsers = PersonsRepository.getRepository().getAll();
        List<Person> personsToSave = allUsers.stream().filter(t -> t.getId() == null).collect(Collectors.toList());
        personDao.save(personsToSave);
        initialize();
    }

    public List<Person> getAll() {
        return PersonsRepository.getRepository().getAll();
    }

    public void addToRepository(Person person){
        PersonsRepository.getRepository().add(person);
    }

    public void initialize() {
        PersonsRepository repository = PersonsRepository.getRepository();
        repository.clear();
        repository.add(personDao.getAll());
    }
}
