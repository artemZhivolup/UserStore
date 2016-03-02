package ua.zhivolup.dao;

import ua.zhivolup.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    private DataSource conection;
    private final String INSERT_NEW = "INSERT INTO user(name, dateOfBirth) VALUES(?, ?);";
    private final String GET_ALL = "SELECT * FROM user;";

    public void save(List<Person> list) {
        try(PreparedStatement statement = conection.getConnection().prepareStatement(INSERT_NEW)) {
            for (Person person : list){
                statement.setString(1, person.getName());
                statement.setTimestamp(2, Timestamp.valueOf(person.getBirthDate().atStartOfDay()));
                statement.execute();
            }
        } catch (SQLException e){
            e.getStackTrace();
        }
    }

    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        try (PreparedStatement statement = conection.getConnection().prepareStatement(GET_ALL)) {
            ResultSet set = statement.executeQuery();
            while (set.next()){
                personList.add(UserMapper.map(set));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }

    public DataSource getConection() {
        return conection;
    }

    public void setConection(DataSource conection) {
        this.conection = conection;
    }
}
