package lorenzofoschetti.u5d13.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
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

    @ManyToMany
    private List<Utente> utentiPartecipanti;
}
