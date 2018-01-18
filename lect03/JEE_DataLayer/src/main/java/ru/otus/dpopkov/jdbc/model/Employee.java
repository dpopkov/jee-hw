package ru.otus.dpopkov.jdbc.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
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
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Position position;
    @Column(nullable = false)
    private BigDecimal salary;
    /*
    Fields which are not used yet:
    private String city;
    private String phone;
    private String email;
    */
}
