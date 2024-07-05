package lorenzofoschetti.u5d13.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazionePayload(


        @NotNull
        UUID utenteId,
        @NotNull
        UUID eventoId
) {
}
