package fr.insy2s.commerce.repositories;

import fr.insy2s.commerce.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
