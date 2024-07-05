package lorenzofoschetti.u5d13.repositories;

import lorenzofoschetti.u5d13.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Utente, UUID> {

    Optional<Utente> findByEmail(String email);
}
