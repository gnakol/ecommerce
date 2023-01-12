package fr.insy2s.commerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="command_line")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="prix_unitaire")
    private double prixUnitaireProduit;

    private int quantité;

    @ManyToOne
    private Commande commande;

    @ManyToOne
    private Produit produit;

}
