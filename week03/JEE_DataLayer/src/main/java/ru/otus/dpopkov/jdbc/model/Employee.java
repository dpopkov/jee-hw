package ru.otus.dpopkov.jdbc.model;

import lombok.Data;

import javax.persistence.*;

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
}
