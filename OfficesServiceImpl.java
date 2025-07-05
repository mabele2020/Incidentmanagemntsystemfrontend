package com.example.project.service.Impl;

import com.example.project.model.Offices;
import com.example.project.repository.OfficesRepository;
import com.example.project.service.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// OfficeServiceImpl.java
@Service
public class OfficesServiceImpl implements OfficesService {
    @Autowired
    private OfficesRepository officeRepository;

    @Override
    public Offices saveOffice(Offices office) {
        return officeRepository.save(office);
    }

    @Override
    public List<Offices> getAllOffices() {
        return officeRepository.findAll();
    }
}
