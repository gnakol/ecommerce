package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Contact;
import fr.insy2s.commerce.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/admin/contact/liste")
    public List<Contact> findAll() {
        return this.contactService.findAll();
    }

    @GetMapping("/admin/contact/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Contact findById(@PathVariable Long id) {
        return this.contactService.findById(id);
    }

    @PostMapping("/public/contact/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Contact> create(@RequestBody Contact newContact) {
        Contact savedContact = this.contactService.create(newContact);
        return ResponseEntity.status(201).body(savedContact);
    }

    @PostMapping("/admin/contact/update/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Contact update(@RequestBody Contact newContact, @PathVariable Long id) {
        if(!id.equals(newContact.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mauvais contact à mettre à jour");
        }
        return this.contactService.update(newContact);
    }

    @DeleteMapping("/admin/contact/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        this.contactService.delete(id);
    }


}
