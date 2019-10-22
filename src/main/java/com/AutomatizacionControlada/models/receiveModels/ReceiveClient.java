package com.AutomatizacionControlada.models.receiveModels;

import com.AutomatizacionControlada.models.Machine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveClient {

    private List<Machine> machineList;

    private String name;
    private String surname;
    private String mail;
    private String phone1;
    private String phone2;
    private Boolean deleted;
}

