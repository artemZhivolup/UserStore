package ua.zhivolup.entity;

import java.time.LocalDate;

public class Person {
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return dateOfBirth;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.dateOfBirth = birthDate;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + dateOfBirth;
    }
}
