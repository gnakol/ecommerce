package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Contact;
import fr.insy2s.commerce.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepo;


    public List<Contact> findAll() {
        return this.contactRepo.findAll();}

    public Contact findById(Long id) {
        Optional<Contact> optContact = this.contactRepo.findById(id);
        if(optContact.isPresent()){
            return optContact.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Contact create(Contact newContact) {
         return this.contactRepo.save(newContact);
    }

    public Contact update(Contact newContact) {
        if(!this.contactRepo.existsById(newContact.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "impossible de trouver le Contact à mettre à jour");
        }
        return this.contactRepo.save(newContact);
    }

    public void delete(Long id) {
        this.contactRepo.deleteById(id);
    }


}
