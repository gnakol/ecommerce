package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Produit;
import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {


    @Autowired
    private ProduitRepository productRepo;



    /**
     * Il renvoie une liste de tous les produits de la base de données
     *
     * @return Une liste de tous les produits dans la base de données.
     */
    public List<Produit> findAll(){
        return this.productRepo.findAll();
    }

    /**
     * Si le produit est présent, retournez-le, sinon lancez une erreur 404
     *
     * @param id L'identifiant du produit à supprimer.
     * @return Le produit avec l'identifiant qui a été transmis.
     */
    public Produit findById(Long id){
        Optional<Produit> optproduit = this.productRepo.findById(id);
        if(optproduit.isPresent()){
            return  optproduit.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Il prend un objet Product, l'enregistre dans la base de données et renvoie l'objet Product
     * enregistré.
     *
     * @param newProduct Il s'agit de l'objet transmis par le contrôleur.
     * @return L'objet newProduct est renvoyé.
     */
    public Produit create(Produit newProduct){
        return this.productRepo.save(newProduct);
    }


    /**
     * Si le produit existe, mettez-le à jour, sinon lancez une exception
     *
     * @param newProduct le nouveau produit à mettre à jour
     */
    public Produit update(Produit newProduct){
        if(!this.productRepo.existsById(newProduct.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"impossible de trouver la Product a mettre a jour ");
        }else{
            return this.productRepo.save(newProduct);
        }
    }

    /**
     * Cette fonction supprime un produit par son identifiant
     *
     * @param id L'identifiant du produit à supprimer.
     */

    public void delete(Long id){
        this.productRepo.deleteById(id);
    }






    public Page<Produit> findProdPaginated(PageRequest pr) {
        return productRepo.findAll(pr);
    }
}
