package annovation.javatokotlin.controller;

import annovation.javatokotlin.entity.UserEntity;
import annovation.javatokotlin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/{users}")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // C
    @PostMapping
    public ResponseEntity<UserEntity> createUser(
            @RequestBody UserCreateRequest userCreateRequest
    ) {
        String username = userCreateRequest.getUsername();
        String email = userCreateRequest.getEmail();

        UserEntity createdUser = userService.createUser(username, email);

        return ResponseEntity
                .status(HttpStatus.CREATED) // http status code 201
                .body(createdUser);
    }

    // R
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") long id) {
        Optional<UserEntity> user = userService.findById(id);

        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); // http status code= 404
    }

    // U
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(
            @PathVariable long id,
            @RequestParam String username
    ) {
        UserEntity updatedUser = userService.updateUser(id, username);

        return ResponseEntity.ok(updatedUser);
    }

    // D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
