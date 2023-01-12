package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Facture;
import fr.insy2s.commerce.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepo;

    public List<Facture> findAll() {return this.factureRepo.findAll();}

    public Facture findById(Long id) {
        Optional<Facture> optFacture =this.factureRepo.findById(id);
        if(optFacture.isPresent()){
            return optFacture.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Facture create (Facture newFacture) {return this.factureRepo.save(newFacture);}

    public Facture update(Facture newFacture){
        if(!this.factureRepo.existsById(newFacture.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Impossible de trouver la facture à mettre à jour");
        } else {
            return this.factureRepo.save(newFacture);
        }
    }

    public void delete(Long id) { this.factureRepo.deleteById(id);}



}
