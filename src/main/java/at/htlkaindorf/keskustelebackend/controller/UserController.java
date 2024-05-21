package at.htlkaindorf.keskustelebackend.controller;


import at.htlkaindorf.keskustelebackend.models.User;
import at.htlkaindorf.keskustelebackend.services.user.UserService;
import at.htlkaindorf.keskustelebackend.services.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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
@Slf4j
public class UserController {
    private final UserServiceImp userServiceImp;

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userServiceImp.getAll().get());
    }

    @PostMapping("/post")
    public ResponseEntity createNewUser(@RequestBody User user) throws Exception {
        log.info("TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTt");
        return ResponseEntity.ok(userServiceImp.createNew(user));
    }

    //@PatchMapping("/")

}
