package fr.insy2s.commerce.repositories;

import fr.insy2s.commerce.models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {

}
