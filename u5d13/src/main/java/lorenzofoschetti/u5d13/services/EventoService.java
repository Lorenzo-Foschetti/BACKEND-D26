package lorenzofoschetti.u5d13.services;


import lorenzofoschetti.u5d13.entities.Evento;
import lorenzofoschetti.u5d13.entities.Utente;
import lorenzofoschetti.u5d13.enums.Role;
import lorenzofoschetti.u5d13.exceptions.BadRequestException;
import lorenzofoschetti.u5d13.exceptions.NotFoundException;
import lorenzofoschetti.u5d13.payloads.NewEventPayload;
import lorenzofoschetti.u5d13.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserService userService;

    public Page<Evento> getEventsList(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return eventoRepository.findAll(pageable);
    }

    public Evento save(NewEventPayload body) {


        Utente found = userService.findById(body.organizzatoreId());

        if (found.getRole() != Role.ORGANIZZATOREEVENTO) {
            throw new BadRequestException("Se non sei un organizzatore non puoi creare un evento");
        }

        Evento evento = new Evento(body.titolo(), body.descrizione(), body.luogo(), body.data(), body.numeroMaxPartecipanti(), found);

        eventoRepository.save(evento);

        return evento;
    }


    public Evento findById(UUID id) {
        return eventoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Evento findByIdAndUpdate(UUID id, Evento eventoModificato) {
        Evento found = findById(id);
        found.setData(eventoModificato.getData());
        found.setLuogo(eventoModificato.getLuogo());
        found.setDescrizione(eventoModificato.getDescrizione());
        found.setNumeroMaxPartecipanti(eventoModificato.getNumeroMaxPartecipanti());


        return eventoRepository.save(found);
    }

    public void findByIdAndDelete(UUID eventoId) {
        Evento found = this.findById(eventoId);
        eventoRepository.delete(found);
    }
}
