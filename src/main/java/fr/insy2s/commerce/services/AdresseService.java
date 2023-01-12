package fr.insy2s.commerce.services;

import fr.insy2s.commerce.dtos.AdresseRequest;
import fr.insy2s.commerce.models.Adresse;
import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.repositories.AdresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdresseService {


    private final AdresseRepository adresseRepo;

    private final UtilisateurService userService;

    public List<Adresse> findAll() {
        return this.adresseRepo.findAll();
    }

    public Adresse findById(Long id) {
        Optional<Adresse> optAdresse = this.adresseRepo.findById(id);
        return optAdresse.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public Adresse create(Adresse newAdresse) {
        return this.adresseRepo.save(newAdresse);
    }

    public Adresse update(Adresse newAdresse) {
        if(!this.adresseRepo.existsById(newAdresse.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "impossible de trouver l'adresse à mettre à jour");
        } else {
            return this.adresseRepo.save(newAdresse);
        }
    }

//       TODO
    public Adresse addUserToAdress(AdresseRequest addRequest) {
        Optional<Adresse> address = adresseRepo.findById(addRequest.getAdresseId());
        Utilisateur user = userService.findById(addRequest.getUserId());
        if (address.isPresent()) {
            address.get().setUtilisateur(user);
            return address.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(Long id) {
        this.adresseRepo.deleteById(id);
    }

//    public Adresse addUserToAdress(AdresseRequest addRequest) {
//    }
}
