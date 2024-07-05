package lorenzofoschetti.u5d13.payloads;

import java.time.LocalDateTime;

public record ErrorsPayload(String message, LocalDateTime errorTime) {
}
