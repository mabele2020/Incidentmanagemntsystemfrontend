package com.example.project.controller;

import com.example.project.model.Offices;
import com.example.project.service.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficesController {

    @Autowired
    private OfficesService officeService;

    @PostMapping
    public ResponseEntity<Offices> addOffice(@RequestBody Offices office) {
        return new ResponseEntity<>(officeService.saveOffice(office), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Offices>> getOffices() {
        return new ResponseEntity<>(officeService.getAllOffices(), HttpStatus.OK);
    }
}
