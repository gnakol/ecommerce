package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Commande;
import fr.insy2s.commerce.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping("/public/commande/liste")
    public List<Commande> findAll() { return this.commandeService.findAll(); }

    @GetMapping("/public/commande/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Commande findById(@PathVariable Long id) { return this.commandeService.findById(id); }

    @PostMapping("/public/commande/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Commande> create(@RequestBody Commande newCommande){
        Commande savedCommande = this.commandeService.create(newCommande);
        return ResponseEntity.status(201).body(savedCommande);

    }

    @PostMapping("/public/commande/update/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Commande update(@RequestBody Commande newCommande, @PathVariable Long id) {
        if(!id.equals(newCommande.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mauvaise commande à mettre à jour");
        }
        return this.commandeService.update(newCommande);
    }

    @DeleteMapping("/public/commande/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        this.commandeService.delete(id);
    }



}
