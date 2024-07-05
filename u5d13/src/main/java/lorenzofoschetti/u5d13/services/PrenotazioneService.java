package lorenzofoschetti.u5d13.services;

import lorenzofoschetti.u5d13.entities.Evento;
import lorenzofoschetti.u5d13.entities.Prenotazione;
import lorenzofoschetti.u5d13.entities.Utente;
import lorenzofoschetti.u5d13.exceptions.BadRequestException;
import lorenzofoschetti.u5d13.payloads.PrenotazionePayload;
import lorenzofoschetti.u5d13.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private EventoService eventoService;

    public Prenotazione savePrenotazione(PrenotazionePayload payload) {
        Utente utente = userService.findById(payload.utenteId());
        Evento evento = eventoService.findById(payload.eventoId());

        if (prenotazioneRepository.findPrenotazioniByEventId(payload.eventoId()).size() >= evento.getNumeroMaxPartecipanti()) {

            throw new BadRequestException("Numero limite gia raggiunto!!!");

        } else {
            Prenotazione newPrenotazione = new Prenotazione(utente, evento);
            return prenotazioneRepository.save(newPrenotazione);
        }


    }
}
