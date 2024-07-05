package lorenzofoschetti.u5d13.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Prenotazione {

    @Id
    @GeneratedValue

    private UUID id;


    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;


    public Prenotazione(Utente utenti, Evento evento) {
        this.utente = utenti;
        this.evento = evento;
    }
}
