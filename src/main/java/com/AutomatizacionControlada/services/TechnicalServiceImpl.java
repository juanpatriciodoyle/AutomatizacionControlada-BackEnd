package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.TechnicalService;
import com.AutomatizacionControlada.repository.TechnicalServiceRepository;
import com.AutomatizacionControlada.messages.EntityNotFoundMsg;
import com.AutomatizacionControlada.util.Status;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TechnicalServiceImpl implements TechnicalServiceService{
    private final TechnicalServiceRepository technicalServiceRepository;

    public TechnicalServiceImpl(TechnicalServiceRepository technicalServiceRepository) {
        this.technicalServiceRepository = technicalServiceRepository;
    }

    @Transactional
    @Override
    public List<TechnicalService> getAll() {

        List<TechnicalService> technicalServiceList = new ArrayList<>();
        for (TechnicalService technicalService: technicalServiceRepository.findAll()) {
            if (!technicalService.getDeleted() && !technicalService.getStatus().equals(Status.DELIVERED)){
                technicalServiceList.add(technicalService);
            }
        }
        return technicalServiceList;
    }

    @Override
    public List<TechnicalService> getTechnicalServiceDeleted() {
        List<TechnicalService> technicalServiceList = new ArrayList<>();
        for (TechnicalService technicalService: technicalServiceRepository.findAll()) {
            if (technicalService.getDeleted()){
                technicalServiceList.add(technicalService);
            }
        }
        return technicalServiceList;
    }

    @Override
    public List<TechnicalService> getTechnicalServiceMarkAsDone() {
        List<TechnicalService> technicalServiceList = new ArrayList<>();
        for (TechnicalService technicalService: technicalServiceRepository.findAll()) {
            if ((!technicalService.getDeleted()) && technicalService.getStatus().equals(Status.DELIVERED)){
                technicalServiceList.add(technicalService);
            }
        }
        return technicalServiceList;
    }

    @Override
    public TechnicalService getById(Long id) {
        return technicalServiceRepository.findById(id).orElseThrow(EntityNotFoundMsg::new);
    }

    @Override
    public TechnicalService save(TechnicalService technicalService) {
        return technicalServiceRepository.save(technicalService);
    }

    @Override
    public void delete(Long id) {

        TechnicalService technicalService = getById(id);
        technicalService.setDeleted(true);
        technicalServiceRepository.save(technicalService);
    }

    @Override
    public TechnicalService update(Long id, TechnicalService newTechnicalService) {

        return technicalServiceRepository.findById(id).map(
                technicalService -> {
                    technicalService.setEmployee(newTechnicalService.getEmployee());
                    technicalService.setClient(newTechnicalService.getClient());
                    technicalService.setMachine(newTechnicalService.getMachine());
                    technicalService.setDescription(newTechnicalService.getDescription());
                    technicalService.setAdmissionDate(newTechnicalService.getAdmissionDate());
                    technicalService.setPrice(newTechnicalService.getPrice());
                    technicalService.setPaymentMethod(newTechnicalService.getPaymentMethod());
                    technicalService.setStatus(newTechnicalService.getStatus());
                    if (newTechnicalService.getStatus().equals(Status.DELIVERED) && this.getById(id).getEgressDate() == null){
                        technicalService.setEgressDate(new Date());
                    }
                 return technicalServiceRepository.save(technicalService);
                }
        ).orElseThrow(EntityNotFoundMsg::new);
    }

    @Override
    public TechnicalService markAsDone(Long id) {

        return technicalServiceRepository.findById(id).map(
                technicalService -> {
                    technicalService.setStatus(Status.DELIVERED);
                    if (this.getById(id).getEgressDate() == null){
                        technicalService.setEgressDate(new Date());
                    }
                    return technicalServiceRepository.save(technicalService);
                }
        ).orElseThrow(EntityNotFoundMsg::new);
    }
}
