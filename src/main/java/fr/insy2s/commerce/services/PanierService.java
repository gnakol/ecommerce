package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Panier;
import fr.insy2s.commerce.models.Role;
import fr.insy2s.commerce.repositories.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PanierService {

    @Autowired
    private PanierRepository panierRepo;

    public List<Panier> findAll(){
        return this.panierRepo.findAll();
    }

    public Panier findById(Long id){
        Optional<Panier> optPanier = this.panierRepo.findById(id);
        if(optPanier.isPresent()){
            return  optPanier.get();
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Panier create(Panier newPanier){
        return this.panierRepo.save(newPanier);
    }

    public Panier update(Panier newPanier){
        if(!this.panierRepo.existsById(newPanier.getId())){
            throw  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"impossible de trouver le role a mettre a jour ");
        }else{
            return this.panierRepo.save(newPanier);
        }
    }

    public void delete(Long id){
        this.panierRepo.deleteById(id);
    }

}
