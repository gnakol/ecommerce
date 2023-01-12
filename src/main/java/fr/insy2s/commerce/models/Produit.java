package fr.insy2s.commerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String reference;

    private String description;

    @Column(name="prix_ttc")
    private double prixTtc;

    private Integer stock;

    @OneToMany(mappedBy = "produit")
    private List<Image> images;

    @ManyToOne
    private Categorie categorie;

}
