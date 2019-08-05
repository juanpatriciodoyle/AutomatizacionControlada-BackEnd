package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.TechnicalService;
import com.AutomatizacionControlada.repository.TechnicalServiceRepository;
import com.AutomatizacionControlada.messages.EntityNotFoundMsg;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return technicalServiceRepository.findAll();
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
        technicalServiceRepository.delete(technicalService);
    }

    @Override
    public TechnicalService update(Long id, TechnicalService newTechnicalService) {

        return technicalServiceRepository.findById(id).map(
                technicalService -> {
                    technicalService.setEmployee(newTechnicalService.getEmployee());
                    technicalService.setDescription(newTechnicalService.getDescription());
                    technicalService.setAdmissionDate(newTechnicalService.getAdmissionDate());
                    technicalService.setEgressDate(newTechnicalService.getEgressDate());
                    technicalService.setPrice(newTechnicalService.getPrice());
                    technicalService.setPaymentMethod(newTechnicalService.getPaymentMethod());
                    technicalService.setDelivered(newTechnicalService.getDelivered());
                    technicalService.setStatus(newTechnicalService.getStatus());
                 return technicalServiceRepository.save(technicalService);
                }
        ).orElseThrow(EntityNotFoundMsg::new);
    }
}
