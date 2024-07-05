package lorenzofoschetti.u5d13.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginPayload(
        @NotEmpty(message = "email obbligatoria")
        String email,
        @NotEmpty(message = "password obbligatoria")
        String password
) {
}
