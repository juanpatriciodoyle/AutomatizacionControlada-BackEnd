package com.AutomatizacionControlada.models;

import com.AutomatizacionControlada.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "technicalService")
public class TechnicalService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    private Client client;

    @OneToOne(fetch= FetchType.LAZY)
    private Machine machine;

    @OneToOne(fetch= FetchType.LAZY)
    private Employee employee;

    private String description;
    private Date admissionDate;
    private Date egressDate;
    private Double price;
    private String paymentMethod;
    private Status status;

    public TechnicalService(Client client, Machine machine, Employee employee, String description, Date admissionDate, Date egressDate, Double price, String paymentMethod, Status status) {
        this.client = client;
        this.machine = machine;
        this.employee = employee;
        this.description = description;
        this.admissionDate = admissionDate;
        this.egressDate = egressDate;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
}

