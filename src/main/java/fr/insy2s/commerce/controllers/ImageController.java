package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Image;
import fr.insy2s.commerce.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/public/image/liste")
    public List<Image> findAll() {return this.imageService.findAll(); }

    @GetMapping("/public/image/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Image findById(@PathVariable Long id) {
        return this.imageService.findById(id);
    }


    @PostMapping("/public/image/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Image> create (@RequestBody Image newImage) {
        Image savedImage = this.imageService.create(newImage);
        return ResponseEntity.status(201).body(savedImage);
    }

    @PostMapping("/public/image/update/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Image update(@RequestBody Image newImage , @PathVariable Long id) {
        if(!id.equals(newImage.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mauvais image à mettre jour");
        }
        return this.imageService.update(newImage);
    }

    @DeleteMapping("/public/image/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        this.imageService.delete(id);
    }


}
