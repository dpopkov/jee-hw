package ru.otus.dpopkov.jdbc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public BigDecimal getAverageSalary() {
        BigDecimal total = BigDecimal.ZERO;
        for (Employee employee : employeeList) {
            total = total.add(employee.getSalary());
        }
        return total.divide(BigDecimal.valueOf(employeeList.size()), RoundingMode.HALF_UP);
    }
}
