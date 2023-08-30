package com.jsu.postcodeapi.Postcode;

import java.util.List;

import com.jsu.postcodeapi.Suburb.Suburb;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private Long postcode_id;

    // * Actual Postcode
    // This isn't the id because some suburbs share a postcode (e.g Clayton and
    // Notting Hill in VIC)
    @Column(name = "postcode")
    @Getter
    @Setter

    private Integer postcode;

    /**
     * List of Suburbs that this Postcode covers. <br/>
     * It should be initialized with either an empty list or a list of suburbs that
     * do not already have a postcode
     */
    @OneToMany(mappedBy = "suburbs", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<Suburb> suburbs;
}
