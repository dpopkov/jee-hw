package ru.otus.dpopkov.jdbc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employees")
public class Employees {
    @XmlElement(name = "employee", type = Employee.class)
    private List<Employee> employeeList;

    public Employees() {
    }

    public Employees(List<Employee> employees) {
        this.employeeList = employees;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employees) {
        this.employeeList = employees;
    }
}
