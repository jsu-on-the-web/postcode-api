package com.jsu.postcodeapi.Postcode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "postcode")
public class Postcode {
    // * Primary Key 
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // * Actual Postcode 
    // This isn't the id because some suburbs share a postcode (e.g Clayton and Notting Hill in VIC)
    @Column
    @Getter
    @Setter
    private Integer postcode;

    // * Suburb
    // TODO: Replace this with a foreign key to the associated suburb entity. (Each postcode can have MANY suburbs, but each suburb has only ONE postcode)
    @Column
    @Getter
    @Setter
    private String suburb;
}
