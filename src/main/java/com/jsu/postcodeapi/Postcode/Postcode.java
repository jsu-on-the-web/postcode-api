package com.jsu.postcodeapi.Postcode;
import java.util.List;

import com.jsu.postcodeapi.Suburb.Suburb;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    @Column(name = "postcode")
    @Getter
    @Setter
    
    private Integer postcode;

    // * Suburb
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    @Column(name = "suburb")
    @Getter
    @Setter
    private List<Suburb> suburbs;
}
