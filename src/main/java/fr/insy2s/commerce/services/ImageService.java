package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Image;
import fr.insy2s.commerce.models.Panier;
import fr.insy2s.commerce.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepo;

    public List<Image> findAll(){
        return this.imageRepo.findAll();
    }

    public Image findById(Long id){
        Optional<Image> optImage = this.imageRepo.findById(id);
        if(optImage.isPresent()){
            return  optImage.get();
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Image create(Image newImage){
        return this.imageRepo.save(newImage);
    }

    public Image update(Image newImage){
        if(!this.imageRepo.existsById(newImage.getId())){
            throw  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"impossible de trouver le panier a mettre a jour ");
        }else{
            return this.imageRepo.save(newImage);
        }
    }

    public void delete(Long id){
        this.imageRepo.deleteById(id);
    }

}
