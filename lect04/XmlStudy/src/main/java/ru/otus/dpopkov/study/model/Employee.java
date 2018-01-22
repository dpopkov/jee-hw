package ru.otus.dpopkov.study.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "employee")
@XmlType(propOrder = {"id", "name", "age", "position", "roleSet"})
public class Employee {
    private Long id;
    private String name;
    private Integer age;
    private Position position;

    private Set<Role> roleSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

//    @XmlTransient
    public void setAge(Integer age) {
        this.age = age;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @XmlElementWrapper(name = "roleSet")
    @XmlElement(name = "role")
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", position=" + position +
                ", roleSet=" + roleSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getId() != null ? !getId().equals(employee.getId()) : employee.getId() != null) return false;
        if (getName() != null ? !getName().equals(employee.getName()) : employee.getName() != null) return false;
        if (getAge() != null ? !getAge().equals(employee.getAge()) : employee.getAge() != null) return false;
        if (getPosition() != null ? !getPosition().equals(employee.getPosition()) : employee.getPosition() != null)
            return false;
        return getRoleSet() != null ? getRoleSet().equals(employee.getRoleSet()) : employee.getRoleSet() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        result = 31 * result + (getRoleSet() != null ? getRoleSet().hashCode() : 0);
        return result;
    }
}
