package at.htlkaindorf.keskustelebackend.controller;


import at.htlkaindorf.keskustelebackend.models.User;
import at.htlkaindorf.keskustelebackend.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project: keskusteleBackend
 * Created by: Emma Bauer
 * Date: 17/05/2024
 * Time: 08:17
 **/

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAll().get());
    }

    @PostMapping("/post")
    public ResponseEntity createNewUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.createNew(user));
    }

    //@PatchMapping("/")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) throws Exception {
        return ResponseEntity.of(userService.longin(user));
    }

}
