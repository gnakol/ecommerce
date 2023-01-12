package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Categorie;
import fr.insy2s.commerce.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;


    @GetMapping("/public/categorie/liste")
    public List<Categorie> findAll() { return this.categorieService.findAll(); }

    @GetMapping("/admin/categorie/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Categorie findById(@PathVariable Long id) { return this.categorieService.findById(id); }

    @PostMapping("/admin/categorie/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Categorie> create(@RequestBody Categorie newCategorie){
        Categorie savedCategorie = this.categorieService.create(newCategorie);
        return ResponseEntity.status(201).body(savedCategorie);

    }

    @PostMapping("/admin/categorie/update/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Categorie update(@RequestBody Categorie newCategorie, @PathVariable Long id) {
        if(!id.equals(newCategorie.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mauvaise catégorie à mettre à jour");
        }
        return this.categorieService.update(newCategorie);
    }

    @DeleteMapping("/admin/categorie/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        this.categorieService.delete(id);
    }

}
