package lorenzofoschetti.u5d13.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lorenzofoschetti.u5d13.entities.Utente;
import lorenzofoschetti.u5d13.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    //con questo metodo andiamo a creare un token dato un determinato user(dipendente)
    public String createToken(Utente user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token) { // Dato un token verificami se è valido
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);


        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");

        }
    }

    public String extractIdFromToken(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build()
                .parseSignedClaims(token).getPayload().getSubject(); // Il subject è quello che ci interessa, perchè è l'id dell'utente
    }

}
