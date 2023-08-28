package com.jsu.postcodeapi.Suburb;

import com.jsu.postcodeapi.Postcode.Postcode;
import com.jsu.postcodeapi.enums.EState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    // NOTE: Could replace this with a FK to a state object if we decide to give
    // each state their own table
    @Column(name = "state")
    @Getter
    @Setter
    private EState state;

    // * Suburb Population
    @Column(name = "population")
    @Getter
    @Setter
    private Long population;

    // * Postcode
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @Getter
    @Setter
    private Postcode postcode;
}
