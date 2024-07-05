package lorenzofoschetti.u5d13.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Evento {
    @Id
    @GeneratedValue
    private UUID id;

    private String titolo;

    private String descrizione;

    private LocalDate data;

    private String luogo;

    private int numeroMaxPartecipanti;

    @ManyToOne
    @JoinColumn(name = "organizzatoreEvento_id")
    private Utente organizzatoreEvento;

    public Evento(String titolo, String descrizione, String luogo, LocalDate data, int numeroMaxPartecipanti, Utente organizzatoreEvento) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.data = data;
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
        this.organizzatoreEvento = organizzatoreEvento;
    }
}
