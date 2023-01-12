package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Categorie;
import fr.insy2s.commerce.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepo;

    public List<Categorie> findAll() {
       return this.categorieRepo.findAll();
    }

    public Categorie findById(Long id) {
        Optional<Categorie> optCategorie = this.categorieRepo.findById(id);
        if(optCategorie.isPresent()){
            return optCategorie.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Categorie create(Categorie newCategorie) {
        return this.categorieRepo.save(newCategorie);
    }

    public Categorie update(Categorie newCategorie){
        if(!this.categorieRepo.existsById(newCategorie.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"impossible de trouver la catégorie à mettre à jour");
        } else {
            return this.categorieRepo.save(newCategorie);
        }
    }

    public void delete(Long id){
        this.categorieRepo.deleteById(id);
    }
}
