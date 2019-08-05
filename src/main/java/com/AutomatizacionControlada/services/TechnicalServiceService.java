package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.TechnicalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TechnicalServiceService{

    List<TechnicalService> getAll();
    TechnicalService getById(Long id);
    TechnicalService save(TechnicalService technicalService);
    void delete(Long id);
    TechnicalService update(Long id, TechnicalService technicalService);
}
