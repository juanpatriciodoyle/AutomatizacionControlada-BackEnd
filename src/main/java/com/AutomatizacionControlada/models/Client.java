package com.AutomatizacionControlada.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE, fetch= FetchType.LAZY)
    private List<Machine> machineList;

    private String name;
    private String surname;
    private String mail;
    private String phone1;
    private String phone2;
    private Boolean deleted;

    public Client(List<Machine> machineList, String name, String surname, String mail, String phone1, String phone2, Boolean deleted) {
        this.machineList = machineList;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.deleted = deleted;
    }
}
