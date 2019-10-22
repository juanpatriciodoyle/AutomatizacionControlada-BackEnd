package com.AutomatizacionControlada.models;

import com.AutomatizacionControlada.util.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Position position;
    private Boolean deleted;

    public Employee(String name, String surname, Position position,Boolean deleted) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.deleted = deleted;

    }
}
