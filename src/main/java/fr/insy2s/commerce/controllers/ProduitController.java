package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.dtos.ProduitResponse;
import fr.insy2s.commerce.models.Produit;

import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProduitController {

    // Injecter le ProductService dans le ProductController.
// Injecter la dependance automatiquement lors du besoin
    @Autowired
    private ProduitService productService;


        /**
         * Cette fonction est une requête GET qui renvoie une liste de tous les produits
         *
         * @return Une liste de produits
         */
        @GetMapping("/public/produit/list")
        public List<Produit> findAll(){
            return this.productService.findAll();
        }

//        @GetMapping("/public/produit/liste")
//        public List<Produit> getPaginated(@PathVariable int pageNo, @PathVariable int pageSize){
//            return this.productService.findAll(pageNo, pageSize);
//        }
    @GetMapping("/public/produit/liste")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<Produit> findProdAll(@RequestParam int page, @RequestParam int size) {
        PageRequest pr = PageRequest.of(page, size);
        return productService.findProdPaginated(pr);
    }
//

        /**
         * Il renvoie un produit avec l'identifiant donné
         *
         * @param id L'identifiant du produit à récupérer.
         * @return Un objet Produit
         */
        @GetMapping("/public/produit/{id}")
        @ResponseStatus(code= HttpStatus.OK)
        public Produit findById(@PathVariable Long id){
            return this.productService.findById(id);
        }

        /**
         * Cette fonction crée un nouveau produit et le renvoie
         *
         * @param newProduct C'est l'objet qui sera créé.
         * @return Le produit qui a été créé.
         */
    @PostMapping("/admin/produit/create")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<?> create (@RequestBody Produit newProduct){
        Produit produitARenvoy = this.productService.create(newProduct);
        ProduitResponse response = new ProduitResponse(
                produitARenvoy.getLabel(),
                produitARenvoy.getReference(),
                produitARenvoy.getDescription(),
                produitARenvoy.getPrixTtc(),
                "Vous avez ajouté un nouveau produit"
        );
        return ResponseEntity.status(201).body(response);
        }



    @PostMapping("/admin/produit/update/{id}")
    @ResponseStatus(code= HttpStatus.ACCEPTED)
    public ResponseEntity<?> update(@RequestBody Produit produit, @PathVariable Long id){
        if(!id.equals(produit.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mauvais produit à mettre à jour");}
            Produit produitARenvoy = this.productService.update(produit);
            ProduitResponse response = new ProduitResponse(
                    produit.getLabel(),
                    produit.getReference(),
                    produit.getDescription(),
                    produit.getPrixTtc(),
                    "Vous avez mis à jour le produit"
            );
            return ResponseEntity.ok(response);
        }



        /**
         * Cette fonction supprime un produit de la base de données
         *
         * @param id L'identifiant du produit à supprimer.
         * @return Le produit qui a été supprimé.
         */
        @DeleteMapping("/admin/produit/delete/{id}")
        @ResponseStatus(code= HttpStatus.ACCEPTED)
        public void delete(@PathVariable Long id){
            this.productService.delete(id);
        }




}
