package com.example.project.service.Impl;

import com.example.project.model.Incident;
import com.example.project.repository.IncidentRepository;
import com.example.project.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// IncidentServiceImpl.java
@Service
public class IncidentServiceImpl implements IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;

    @Override
    public Incident reportIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    @Override
    public List<Incident> getAllIncidents() {
        return List.of();
    }

}
