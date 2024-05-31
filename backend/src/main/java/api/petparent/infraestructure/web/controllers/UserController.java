package api.petparent.infraestructure.web.controllers;

import api.petparent.application.core.service.UserService;
import api.petparent.infraestructure.web.requests.user.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("petparent/api/v1/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createUser(@RequestBody LoginRequest userRequest) throws ExecutionException, InterruptedException {

        var response = userService.addUser(userRequest);

        return response;
    }

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest userRequest) {

        userService.loginUser(userRequest);

        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {

        userService.deleteUser(email);

        return new ResponseEntity<>("Invalid request, null parameters", HttpStatus.BAD_REQUEST);
    }
}