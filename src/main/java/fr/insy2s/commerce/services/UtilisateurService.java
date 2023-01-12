package fr.insy2s.commerce.services;

import fr.insy2s.commerce.dtos.UpdatePasswordRequest;
import fr.insy2s.commerce.models.Produit;
import fr.insy2s.commerce.models.Role;
import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.repositories.RoleRepository;
import fr.insy2s.commerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Transient;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository userRepo;

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private RoleRepository roleRepo;

    /**
     * Il renvoie une liste de tous les utilisateur de la base de données
     *
     * @return Une liste de tous les utilisateur dans la base de données.
     */
    public List<Utilisateur> findAll(){
        return this.userRepo.findAll();
    }

    /**
     * Si l'Utilisateur est présent, retournez-le, sinon lancez une erreur 404
     *
     * @param id L'identifiant del'utilisateur à supprimer.
     * @return L'utilisateur avec l'identifiant qui a été transmis.
     */
    public Utilisateur findById(Long id){

        Optional<Utilisateur> optproduit = this.userRepo.findById(id);
        if(optproduit.isPresent()){
            return  optproduit.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

//    public ResponseEntity<Utilisateur> addUser(Utilisateur newUtilisateur) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = passwordEncoder.encode(newUtilisateur.getPassword());
//        newUtilisateur.setPassword(password);
//        Optional<Role> role = roleRepo.findById(2L);
//        newUtilisateur.addRole((role.get()));
//        Utilisateur savedUser = userRepo.save(newUtilisateur);
//        return ResponseEntity.status(200).body(savedUser);
//    }


//    public Utilisateur create(Utilisateur newUtilisateur){
//    Optional<Role> role = roleRepo.findById(2L);
//        newUtilisateur.addRole((role.get()));
//        return this.userRepo.save((newUtilisateur));
//    }


    /**
     * Si l'utilisateur existe, mettez-le à jour, sinon lancez une exception
     *
     * @param newProduct le nouveau produit à mettre à jour
     */
    public Utilisateur update(Utilisateur newProduct){
        if(!this.userRepo.existsById(newProduct.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"impossible de trouver la Product a mettre a jour ");
        }else{
            return this.userRepo.save(newProduct);
        }
    }

    public Utilisateur updateRole(Utilisateur userRoleToUpdate){
        if(!this.userRepo.existsById(userRoleToUpdate.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"impossible de trouver le role a mettre a jour ");
        }else{
            Utilisateur user = this.findById(userRoleToUpdate.getId());
            user.setRoles(userRoleToUpdate.getRoles());
            return this.userRepo.save(user);
        }
    }

//    public ResponseEntity<Utilisateur> addUser(Utilisateur utilisateur) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = passwordEncoder.encode(utilisateur.getPassword());
//        utilisateur.setPassword(password);
//        Utilisateur savedUser = userRepo.save(utilisateur);
//        URI userURI = URI.create("/user/" + savedUser.getId());
//        return ResponseEntity.created(userURI).body(savedUser);
//    }


    public ResponseEntity<Utilisateur> addUser(Utilisateur newUtilisateur) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(newUtilisateur.getPassword());
        newUtilisateur.setPassword(password);
        // 2 correspon à l'Id du Role [ROLE_CLIENT] dans db,L = Long
        // pour attribuer le role client aux utilisateur d'une facon automatique
        Optional<Role> role = roleRepo.findById(2L);
        newUtilisateur.addRole((role.get()));
        Utilisateur savedUser = userRepo.save(newUtilisateur);
        return ResponseEntity.status(200).body(savedUser);
    }


    public UUID forgetPassword(String email){
        Optional<Utilisateur> user = this.userRepo.findByEmail(email);
        if(user.isPresent()){

            UUID token = UUID.randomUUID();
            user.get().setResetToken(token.toString());
            this.update(user.get());
            senderService.sendSimpleEmail(user.get().getEmail(),
                    "Changer mot de passe",

                    "http://localhost:5173/updatepass/" + token);
            return token ;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utilisateur n'existe pas, vous devez vous inscrire");
    }



    public ResponseEntity<?> updatePassword( UpdatePasswordRequest request) {
        Optional<Utilisateur> user = this.userRepo.findByResetToken(request.getResetToken());
        if (user.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            Utilisateur user1 = (this.userRepo.findByResetToken(request.getResetToken()).get());
            Utilisateur user1 = user.get();
            String password = passwordEncoder.encode(request.getNewPassword());
            user1.setPassword(password);
            this.userRepo.save(user1);
            return ResponseEntity.status(201).body("Votre mot de passe à été changé");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utilisateur n'existe pas, vous devez vous inscrire");
        }
    }



    /**
     * Cette fonction supprime un utilisateur par son identifiant
     *
     * @param id L'identifiant de l'utilisateur à supprimer.
     */

    public void delete(Long id){
        this.userRepo.deleteById(id);
    }

    public Page<Utilisateur> findUserPaginated(PageRequest pr) {
        return userRepo.findAll(pr);
    }

//    public List<Utilisateur> findUserPaginated(int pageNo, int pageSize) {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<Utilisateur> pagedResult = userRepo.findAll(paging);
//        return pagedResult.toList();
//    }


}
