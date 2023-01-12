package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Produit;
import fr.insy2s.commerce.models.Role;
import fr.insy2s.commerce.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public List<Role> findAll(){
        return this.roleRepo.findAll();
    }

    public Role findById(Long id){
        Optional<Role> optRole = this.roleRepo.findById(id);
        if(optRole.isPresent()){
            return  optRole.get();
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Role create(Role newRole){
        return this.roleRepo.save(newRole);
    }

    public Role update(Role newRole){
        if(!this.roleRepo.existsById(newRole.getId())){
            throw  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"impossible de trouver le role a mettre a jour ");
        }else{
            return this.roleRepo.save(newRole);
        }
    }

    public void delete(Long id){
        this.roleRepo.deleteById(id);
    }


}
