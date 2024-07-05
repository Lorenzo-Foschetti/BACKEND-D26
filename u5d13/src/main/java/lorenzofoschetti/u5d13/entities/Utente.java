package lorenzofoschetti.u5d13.entities;

import jakarta.persistence.*;
import lombok.*;
import lorenzofoschetti.u5d13.enums.Role;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Utente {


    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(name = "utenti_eventi",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id"))
    private List<Evento> eventi;
}
