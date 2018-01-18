package ru.otus.dpopkov.jdbc.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Position {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "position")
    private Set<Employee> employees = new HashSet<>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Role> roles = new HashSet<>();
}
