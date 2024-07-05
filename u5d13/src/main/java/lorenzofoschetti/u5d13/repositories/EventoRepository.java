package lorenzofoschetti.u5d13.repositories;

import lorenzofoschetti.u5d13.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
}
