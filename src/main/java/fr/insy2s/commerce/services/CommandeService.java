package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Commande;
import fr.insy2s.commerce.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandRepo;

    public List<Commande> findAll() {
        return this.commandRepo.findAll();
    }

    public Commande findById(Long id) {
        Optional<Commande> optCommande = this.commandRepo.findById(id);
        if (optCommande.isPresent()) {
            return optCommande.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Commande create( Commande newCommande) {
        return this.commandRepo.save(newCommande);
    }

    public Commande update(Commande newCommande) {
        if(!this.commandRepo.existsById(newCommande.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "impossible de trouver la commande à mettre à jour");
        }
        return this.commandRepo.save(newCommande);
    }

    public void delete(Long id) {
         this.commandRepo.deleteById(id);
    }

}
