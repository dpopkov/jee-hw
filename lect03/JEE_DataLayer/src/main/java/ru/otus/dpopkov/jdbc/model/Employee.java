package ru.otus.dpopkov.jdbc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String login;
    private Integer salt;
    private Integer passwordHash;
    @XmlTransient
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Position position;
    @Column(nullable = false)
    private BigDecimal salary;

    public Employee(Long id, String fullName, BigDecimal salary) {
        this.id = id;
        this.fullName = fullName;
        this.salary = salary;
    }

    /*
    Fields which are not used yet:
    private String city;
    private String phone;
    private String email;
    */
}
