package com.equipementservice.model;


import com.equipementservice.enums.RessourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    private int qty ;

    @Enumerated(EnumType.STRING)
    private RessourceType type ;

    private String fournisseur ;

    @Column
    private Long tacheId ;



}
