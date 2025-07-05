package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class Offices {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
