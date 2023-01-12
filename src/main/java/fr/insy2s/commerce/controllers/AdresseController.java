package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.dtos.AdresseRequest;
import fr.insy2s.commerce.models.Adresse;

import fr.insy2s.commerce.services.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdresseController {

    @Autowired
    private AdresseService adresseService;


    @GetMapping("/public/adresse/liste")
    public List<Adresse> findAll() { return this.adresseService.findAll(); }

    @GetMapping("/public/adresse/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Adresse findById(@PathVariable Long id) { return this.adresseService.findById(id); }

    @PostMapping("/public/adresse/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Adresse> create(@RequestBody Adresse newAdresse){
        Adresse savecAdresse = this.adresseService.create(newAdresse);
        return ResponseEntity.status(201).body(savecAdresse);
    }

    @PostMapping("/public/adresse/update/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Adresse update(@RequestBody Adresse newAdresse, @PathVariable Long id) {
        if(!id.equals(newAdresse.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mauvais Panier à mettre à jour");
        }
        return this.adresseService.update(newAdresse);
    }

    @PostMapping("/public/adresse/updateUser")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Adresse updateUserAddress(@RequestBody AdresseRequest addRequest){

        return this.adresseService.addUserToAdress(addRequest);
    }

    @DeleteMapping("/public/adresse/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        this.adresseService.delete(id);
    }

}
