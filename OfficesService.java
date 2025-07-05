package com.example.project.service;

import com.example.project.model.Offices;

import java.util.List;

public interface OfficesService {
    Offices saveOffice(Offices office);
    List<Offices> getAllOffices();
}
