package com.AutomatizacionControlada.models.receiveModels;

import com.AutomatizacionControlada.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveTechnicalService {

    private long client;
    private long machine;
    private long employee;

    private String description;
    private Date admissionDate;
    private Date egressDate;
    private Double price;
    private String paymentMethod;
    private Status status;

}

