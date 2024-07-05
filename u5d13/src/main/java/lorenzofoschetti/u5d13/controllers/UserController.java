package lorenzofoschetti.u5d13.controllers;


import lorenzofoschetti.u5d13.entities.Utente;
import lorenzofoschetti.u5d13.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private Page<Utente> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.userService.getUtenti(page, size, sortBy);
    }


    @GetMapping("/{dipendenteId}")
    public Utente findById(@PathVariable UUID utenteId) {
        return userService.findById(utenteId);
    }

}
