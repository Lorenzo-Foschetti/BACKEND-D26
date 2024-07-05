package lorenzofoschetti.u5d13.services;


import lorenzofoschetti.u5d13.entities.Utente;
import lorenzofoschetti.u5d13.exceptions.UnauthorizedException;
import lorenzofoschetti.u5d13.payloads.UserLoginPayload;
import lorenzofoschetti.u5d13.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginPayload payload) {


        Utente user = this.userService.findByEmail(payload.email());

        if (user.getPassword().equals(payload.password())) {

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non corrette!");
        }
    }
}
