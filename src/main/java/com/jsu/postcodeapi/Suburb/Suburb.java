package com.jsu.postcodeapi.Suburb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "suburb")
public class Suburb {
    // * Primary Key
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // * Suburb Name
    @Column(name = "suburb_name")
    @Getter
    @Setter
    private String suburbName;
    
    // * Suburb State 
    // NOTE: Could replace this with a FK to a state object if we decide to give each state their own table
    @Column(name = "state")
    @Getter
    @Setter
    private String state;

    // * State Country 
    // NOTE: Also consider replacing this with a FK to a country object if we decide to give each country their own table
    @Column(name = "country")
    @Getter
    @Setter
    private String country;

    //* Suburb Population
    @Column(name = "population")
    @Getter
    @Setter
    private Long population;
}
