package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Role;
import fr.insy2s.commerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/public/role/liste")
    public List<Role> findAll() { return this.roleService.findAll(); }

    @GetMapping("/public/role/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public Role findById(@PathVariable Long id) { return this.roleService.findById(id); }


    @PostMapping("/public/role/create")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Role> create (@RequestBody Role newRole) {
        Role savedRole = this.roleService.create(newRole);
        return ResponseEntity.status(201).body(savedRole);
    }

    @PostMapping("/public/role/update/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Role update (@RequestBody Role newRole, @PathVariable Long id) {
        if(!id.equals(newRole.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mausvais role à mettre à jour");
        }
        return this.roleService.update(newRole);
    }

    @DeleteMapping("/public/role/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) { this.roleService.delete(id);}

}
