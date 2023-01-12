package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Facture;
import fr.insy2s.commerce.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @GetMapping("/admin/facture/liste")
    public List<Facture> findAll() {
        return this.factureService.findAll();
    }

    @GetMapping("/admin/facture/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public Facture findById(@PathVariable Long id) {
        return this.factureService.findById(id);
    }

    @PostMapping("/admin/facture/create")
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<Facture> create (@RequestBody Facture newFacture) {
        Facture savedFacture = this.factureService.create(newFacture);
        return ResponseEntity.status(201).body(savedFacture);
    }

    @PostMapping("/admin/facture/update/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public Facture update(@RequestBody Facture newFacture, @PathVariable Long id) {
        if(!id.equals(newFacture.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mauvaise Facture à mettre à jour");
        } else {
            return this.factureService.update(newFacture);
        }
    }


        @DeleteMapping("/admin/facture/delete/{id}")
        @ResponseStatus(code = HttpStatus.ACCEPTED)
        private void delete(@PathVariable Long id) {
            this.factureService.delete(id);
        }









}
