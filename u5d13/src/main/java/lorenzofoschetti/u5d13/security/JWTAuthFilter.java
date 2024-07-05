package lorenzofoschetti.u5d13.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lorenzofoschetti.u5d13.entities.Utente;
import lorenzofoschetti.u5d13.exceptions.UnauthorizedException;
import lorenzofoschetti.u5d13.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new

                    UnauthorizedException("Per favore inserisci correttamente il token nell'header");

        String accessToken = authHeader.substring(7);

        jwtTools.verifyToken(accessToken);

        String userId = jwtTools.extractIdFromToken(accessToken);
        Utente currentUser = userService.findById(UUID.fromString(userId));

        // 4.2 Trovato l'utente posso associarlo al Security Context, praticamente questo equivale ad 'associare' l'utente autenticato alla richiesta corrente
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
        // Il terzo parametro è OBBLIGATORIO se si vuol poter usare i vari @PreAuthorize perché esso contiene la lista dei ruoli dell'utente
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

        //se è tutto ok mandiamo un 401, ma dobbiamo ancora gestire questa cosa
    }
}
