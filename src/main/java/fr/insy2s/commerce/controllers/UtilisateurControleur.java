package fr.insy2s.commerce.controllers;


import fr.insy2s.commerce.dtos.UpdatePasswordRequest;
import fr.insy2s.commerce.models.Produit;
import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.services.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")

public class UtilisateurControleur {

//        @Autowired
//        private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService userService;

    @PostMapping("/public/user/create")
    public ResponseEntity<Utilisateur> create(@RequestBody Utilisateur utilisateur) {
        return this.userService.addUser(utilisateur);
    }


    @GetMapping("/public/user/list")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Utilisateur> list() {
        return userService.findAll();
    }


    @GetMapping("/public/user/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Utilisateur findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PostMapping("public/user/update/{id}")
    @ResponseStatus(code= HttpStatus.ACCEPTED)
    public Utilisateur update (@RequestBody Utilisateur utilisateur, @PathVariable Long id){
        if (!id.equals(utilisateur.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"mauvais utilisateur à mettre a jour");
        }
        return this.userService.update(utilisateur);
    }

    @PostMapping("public/user/updateRole/{id}")
    @ResponseStatus(code= HttpStatus.ACCEPTED)
    public Utilisateur updateRole (@RequestBody Utilisateur utilisateur, @PathVariable Long id){
        if (!id.equals(utilisateur.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"mauvais utilisateur à mettre a jour");
        }
        return this.userService.updateRole(utilisateur);
    }
   @PostMapping("public/user/forgetPass")
   @ResponseStatus(code= HttpStatus.ACCEPTED)
   public UUID forgetPassword(@RequestBody Utilisateur user ){
      return this.userService.forgetPassword(user.getEmail());
    }

    @PostMapping("public/user/updatePass")
    @ResponseStatus(code= HttpStatus.ACCEPTED)
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request){
        return this.userService.updatePassword(request);
    }




    @DeleteMapping("/public/user/delete/{id}")
    @ResponseStatus(code= HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {this.userService.delete(id);}

    @GetMapping("/public/user/liste")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<Utilisateur> findAll(@RequestParam int page, @RequestParam int size) {
        PageRequest pr = PageRequest.of(page, size);
        return userService.findUserPaginated(pr);
    }

//    @GetMapping("/public/produit/liste/{pageNo}/{pageSize}")
//    public Page<Utilisateur> getPaginated(@RequestParam int pageNo, @RequestParam int pageSize){
//        return (Page<Utilisateur>) this.userService.findUserPaginated(pageNo, pageSize);
//    }

}








