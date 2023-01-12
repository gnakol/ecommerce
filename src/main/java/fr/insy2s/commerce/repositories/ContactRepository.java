package fr.insy2s.commerce.repositories;

import fr.insy2s.commerce.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
