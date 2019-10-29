package com.AutomatizacionControlada.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String internalCode;
    private String brand;
    private String model;
    private Boolean boughtHere;
    private Boolean deleted;


    public Machine(String internalCode, String brand, String model, Boolean boughtHere, Boolean deleted) {
        this.internalCode = internalCode;
        this.brand = brand;
        this.model = model;
        this.boughtHere = boughtHere;
        this.deleted = deleted;

    }
}
