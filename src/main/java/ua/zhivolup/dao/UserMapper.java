package ua.zhivolup.dao;

import ua.zhivolup.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;

public class UserMapper {
    public static Person map(ResultSet resultSet){
        Person person = new Person();
        try {
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            Timestamp timestamp = resultSet.getTimestamp("dateOfBirth");
            int year = timestamp.toLocalDateTime().getYear();
            Month month = timestamp.toLocalDateTime().getMonth();
            int day = timestamp.toLocalDateTime().getDayOfMonth();
            LocalDate dateOfBirth = LocalDate.of(year, month, day);
            person.setBirthDate(dateOfBirth);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }
}
