package com.example.project.controller;

import com.example.project.IncidentDTO;
import com.example.project.model.Incident;
import com.example.project.model.Offices;
import com.example.project.repository.IncidentRepository;
import com.example.project.repository.OfficesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "http://localhost:5173")
public class IncidentController {

    private final IncidentRepository incidentRepo;
    private final OfficesRepository officeRepo;

    public IncidentController(IncidentRepository incidentRepo, OfficesRepository officeRepo) {
        this.incidentRepo = incidentRepo;
        this.officeRepo = officeRepo;
    }

    @PostMapping
    public ResponseEntity<?> createIncident(@RequestBody IncidentDTO dto) {
        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Description must not be empty.");
        }
        Long officeId = dto.getOfficeId();
        if (officeId == null || officeId <= 0) {
            return ResponseEntity.badRequest().body("Please enter a valid office number.");
        }
        Offices office;
        try {
            office = officeRepo.getReferenceById(officeId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Office number " + officeId + " does not exist.");
        }
        Incident incident = new Incident();
        incident.setDescription(dto.getDescription().trim());
        incident.setOffice(office);
        Incident saved = incidentRepo.save(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentRepo.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getIncidentById(@PathVariable Long id) {
        Optional<Incident> opt = incidentRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Incident with id " + id + " not found.");
        }
        return ResponseEntity.ok(opt.get());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncident(
            @PathVariable Long id,
            @RequestBody IncidentDTO dto
    ) {
        Optional<Incident> opt = incidentRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Incident with id " + id + " not found.");
        }

        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Description must not be empty.");
        }
        Long newOfficeId = dto.getOfficeId();
        if (newOfficeId == null || newOfficeId <= 0) {
            return ResponseEntity.badRequest().body("Please enter a valid office number.");
        }

        Offices newOffice;
        try {
            newOffice = officeRepo.getReferenceById(newOfficeId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Office number " + newOfficeId + " does not exist.");
        }

        Incident existing = opt.get();
        existing.setDescription(dto.getDescription().trim());
        existing.setOffice(newOffice);
        Incident updated = incidentRepo.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncident(@PathVariable Long id) {
        Optional<Incident> opt = incidentRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Incident with id " + id + " not found.");
        }
        incidentRepo.delete(opt.get());
        return ResponseEntity.noContent().build();
    }
}
