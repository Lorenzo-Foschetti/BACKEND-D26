package lorenzofoschetti.u5d13.repositories;

import lorenzofoschetti.u5d13.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    List<Prenotazione> findPrenotazioniByEventId(UUID eventId);

}
