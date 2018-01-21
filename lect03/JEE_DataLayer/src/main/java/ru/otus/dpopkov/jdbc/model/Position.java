package ru.otus.dpopkov.jdbc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@XmlRootElement(name = "position")
public class Position {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "position")
    @XmlTransient
    private Set<Employee> employees = new HashSet<>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    @XmlTransient
    private Set<Role> roles = new HashSet<>();

    public Position(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
