package at.htlkaindorf.keskustelebackend.controller.auth;

import at.htlkaindorf.keskustelebackend.config.auth.JwtService;
import at.htlkaindorf.keskustelebackend.models.User;
import at.htlkaindorf.keskustelebackend.models.user.LoginResponse;
import at.htlkaindorf.keskustelebackend.models.user.LoginUserDto;
import at.htlkaindorf.keskustelebackend.models.user.RegisterUserDto;
import at.htlkaindorf.keskustelebackend.services.user.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 07/06/2024
 * Time: 08:13
 **/
@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setEmail(authenticatedUser.getEmail());
        loginResponse.setUsername(authenticatedUser.getUsername());
        loginResponse.setId(authenticatedUser.getId());
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
