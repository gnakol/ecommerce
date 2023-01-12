package fr.insy2s.commerce.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


/**
 * @author Hocine Bedrouni
 */
@RestController
@RequestMapping("/api")
public class GreetingsController {

    @GetMapping("/public/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from our API");
    }

    @GetMapping("/bye")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("Good by and see you later");
    }
}
