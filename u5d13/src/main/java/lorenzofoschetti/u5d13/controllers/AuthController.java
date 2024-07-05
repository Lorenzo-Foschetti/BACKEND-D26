package lorenzofoschetti.u5d13.controllers;


import lorenzofoschetti.u5d13.entities.Utente;
import lorenzofoschetti.u5d13.exceptions.BadRequestException;
import lorenzofoschetti.u5d13.payloads.NewUserPayload;
import lorenzofoschetti.u5d13.payloads.UserLoginPayload;
import lorenzofoschetti.u5d13.payloads.UserLoginResponsePayload;
import lorenzofoschetti.u5d13.services.AuthService;
import lorenzofoschetti.u5d13.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginResponsePayload login(@RequestBody UserLoginPayload payload) {
        return new UserLoginResponsePayload(authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)

    private Utente saveUser(@RequestBody @Validated NewUserPayload body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            throw new BadRequestException(validationResult.getAllErrors());
        }
        return userService.save(body);
    }
}
