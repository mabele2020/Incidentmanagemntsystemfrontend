package com.example.project.service;

import com.example.project.model.Incident;

import java.util.List;


public interface IncidentService {
    Incident reportIncident(Incident incident);

    List<Incident> getAllIncidents();
}
