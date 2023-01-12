package fr.insy2s.commerce.repositories;

import fr.insy2s.commerce.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}
