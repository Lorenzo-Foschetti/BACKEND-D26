package lorenzofoschetti.u5d13.controllers;

import lorenzofoschetti.u5d13.entities.Evento;
import lorenzofoschetti.u5d13.exceptions.BadRequestException;
import lorenzofoschetti.u5d13.payloads.NewEventPayload;
import lorenzofoschetti.u5d13.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/eventSaved")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATOREEVENTO')")

    private Evento saveEvent(@RequestBody @Validated NewEventPayload body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            throw new BadRequestException(validationResult.getAllErrors());
        }
        return eventoService.save(body);
    }

    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATOREEVENTO')")
    public Evento findByIdAndUpdate(@PathVariable UUID eventoId, @RequestBody Evento eventoModificato) {
        return eventoService.findByIdAndUpdate(eventoId, eventoModificato);
    }

    @DeleteMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATOREEVENTO')")
    public void findByIdAndDelete(@PathVariable UUID eventoId) {
        eventoService.findByIdAndDelete(eventoId);

    }
}

