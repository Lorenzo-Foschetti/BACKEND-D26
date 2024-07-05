package lorenzofoschetti.u5d13.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NewUserPayload(

        @NotEmpty(message = "Il nome  è  obbligatorio!")

        String name,
        @NotEmpty(message = "Il cognome è  obbligatorio!")

        String surname,
        @NotEmpty(message = "L'email è obbligatoria!")

        String email,
        @NotEmpty(message = "La password è obbligatoria!")

        String password
) {
}
