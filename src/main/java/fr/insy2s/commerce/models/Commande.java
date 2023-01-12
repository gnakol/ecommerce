package fr.insy2s.commerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commande {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date_commande;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_commande")
    private CommandeStatus statutCommande;

    private Date date_livraison;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToOne
    private Facture facture;

    @ManyToOne
    private Adresse adresseLivraison;

    @ManyToOne
    private Adresse adresseFacturation;

    @OneToMany(mappedBy = "commande")
    private List<Panier> paniers;

}
