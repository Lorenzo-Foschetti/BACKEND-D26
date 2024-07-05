package lorenzofoschetti.u5d13.services;


import lorenzofoschetti.u5d13.entities.Utente;
import lorenzofoschetti.u5d13.exceptions.BadRequestException;
import lorenzofoschetti.u5d13.exceptions.NotFoundException;
import lorenzofoschetti.u5d13.payloads.NewUserPayload;
import lorenzofoschetti.u5d13.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public Page<Utente> getUtenti(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public Utente save(NewUserPayload body) {

        this.userRepository.findByEmail(body.email()).ifPresent(

                user -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );


        Utente newUser = new Utente(body.name(), body.surname(), body.email(), bcrypt.encode(body.password()));


        return userRepository.save(newUser);
    }


    public Utente findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Utente findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }
}
