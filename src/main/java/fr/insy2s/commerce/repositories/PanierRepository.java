package fr.insy2s.commerce.repositories;

import fr.insy2s.commerce.models.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Long> {

}
