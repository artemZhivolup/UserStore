package ua.zhivolup.dao;

import org.junit.Before;
import org.junit.Test;
import ua.zhivolup.entity.Person;

import java.util.List;

public class UserDaoTest {

    PersonDao personDao = new PersonDao();

    @Before
    public void before(){
        DataSource dataSource = new DataSource();
        personDao.setConection(dataSource);
    }

    @Test
    public void testGetAllUsersFromDataBase(){
        List<Person> personList = personDao.getAll();
        for (Person person : personList) {
            System.out.println(person);
        }
    }
}