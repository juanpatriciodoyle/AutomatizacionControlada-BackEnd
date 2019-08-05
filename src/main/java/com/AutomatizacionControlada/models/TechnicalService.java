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

    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;

    private String description;
    private Date admissionDate;
    private Date egressDate;
    private Double price;
    private String paymentMethod;
    private Boolean delivered;
    private Enum<Status> status;

    public TechnicalService(Employee employee, String description, Date admissionDate, Date egressDate, Double price, String paymentMethod, Boolean delivered, Enum<Status> status) {
        this.employee = employee;
        this.description = description;
        this.admissionDate = admissionDate;
        this.egressDate = egressDate;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.delivered = delivered;
        this.status = status;
    }
}

