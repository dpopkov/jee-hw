package ru.otus.dpopkov.jdbc.dao;

import ru.otus.dpopkov.jdbc.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();
    String getNameWithMaxSalary();
    void remove(Long id);
}
