package at.htlkaindorf.keskustelebackend.services.user;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 07/06/2024
 * Time: 08:48
 **/
import at.htlkaindorf.keskustelebackend.models.User;
import at.htlkaindorf.keskustelebackend.models.user.LoginUserDto;
import at.htlkaindorf.keskustelebackend.models.user.RegisterUserDto;
import at.htlkaindorf.keskustelebackend.repos.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepo userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepo userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findUserByEmail(input.getEmail())
                .orElseThrow();
    }
}
