package fr.insy2s.commerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProduitResponse {

        private String label;

        private String reference;
        
        private String description;

        private double prixTtc;

        private String message;


}
