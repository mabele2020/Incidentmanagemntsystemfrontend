package com.example.project.repository;

import com.example.project.model.Offices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficesRepository extends JpaRepository<Offices, Long> {
    // No additional methods needed—findById, save, delete, etc. are inherited
}
