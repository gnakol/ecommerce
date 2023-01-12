package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Panier;

import fr.insy2s.commerce.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api")
public class PanierController {
    @Autowired
    private PanierService panierService;

    @GetMapping("/admin/panier/liste")
    public List<Panier> findAll() { return this.panierService.findAll(); }

    @GetMapping("/public/panier/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Panier findById(@PathVariable Long id) { return this.panierService.findById(id); }

    @PostMapping("/public/panier/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Panier> create(@RequestBody Panier newPanier){
        Panier savedPanier = this.panierService.create(newPanier);
        return ResponseEntity.status(201).body(savedPanier);

    }

    @PostMapping("/public/panier/update/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Panier update(@RequestBody Panier newPanier, @PathVariable Long id) {
        if(!id.equals(newPanier.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mauvais Panier à mettre à jour");
        }
        return this.panierService.update(newPanier);
    }

    @DeleteMapping("/public/panier/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        this.panierService.delete(id);
    }

}
